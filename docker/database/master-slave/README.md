# 基于docker-compose的mysql主从搭建

本目录包含基于docker-compose快速搭建mysql主从需要的文件

## 文件说明

- iflow.sql： 数据库初始化脚本，根据需要替换为自己的即可

- init-master.sh： 主库初始化shell

```
#!/bin/bash

echo ">>>>start to init master"

set -e
 
# create replication user
 
#mysql_net=$(ip route | awk '$1=="default" {print $3}' | sed "s/\.[0-9]\+$/.%/g")
 
MYSQL_PWD=${MYSQL_ROOT_PASSWORD} mysql -u root \
-e "CREATE USER '${MYSQL_REPLICATION_USER}'@'%' IDENTIFIED BY '${MYSQL_REPLICATION_PASSWORD}'; \
GRANT REPLICATION SLAVE ON *.* TO '${MYSQL_REPLICATION_USER}'@'%';"
```

- init-node： 从库初始化shell

```
#!/bin/bash

#check mysql master run status

echo ">>>>start to init node"

set -e

until MYSQL_PWD=${MASTER_MYSQL_ROOT_PASSWORD} mysql -u root -h mysql-master ; do
  >&2 echo "MySQL master is unavailable - sleeping"
  sleep 3
done

# create replication user

#mysql_net=$(ip route | awk '$1=="default" {print $3}' | sed "s/\.[0-9]\+$/.%/g")

MYSQL_PWD=${MASTER_MYSQL_ROOT_PASSWORD} mysql -u root \
-e "CREATE USER '${MYSQL_REPLICATION_USER}'@'%' IDENTIFIED BY '${MYSQL_REPLICATION_PASSWORD}'; \
GRANT REPLICATION SLAVE ON *.* TO '${MYSQL_REPLICATION_USER}'@'%';"

# get master log File & Position

master_status_info=$(MYSQL_PWD=${MASTER_MYSQL_ROOT_PASSWORD} mysql -u root -h mysql-master -e "show master status\G")

LOG_FILE=$(echo "${master_status_info}" | awk 'NR!=1 && $1=="File:" {print $2}')
LOG_POS=$(echo "${master_status_info}" | awk 'NR!=1 && $1=="Position:" {print $2}')

# set node master

MYSQL_PWD=${NODE_MYSQL_ROOT_PASSWORD} mysql -u root \
-e "CHANGE MASTER TO MASTER_HOST='mysql-master', \
MASTER_USER='${MYSQL_REPLICATION_USER}', \
MASTER_PASSWORD='${MYSQL_REPLICATION_PASSWORD}', \
MASTER_LOG_FILE='${LOG_FILE}', \
MASTER_LOG_POS=${LOG_POS};"

# start slave and show slave status

MYSQL_PWD=${MYSQL_ROOT_PASSWORD} mysql -u root -e "START SLAVE;show slave status\G"

```

- docker-compose.yml:  

```
version: "3.3"
 
services:
  
  mysql-master: &mysql
    image: mysql:${TAG}
    container_name: mysql-iflow-master
    restart: unless-stopped
    env_file:
      - .env
    environment:
      - MYSQL_ROOT_PASSWORD=${MASTER_MYSQL_ROOT_PASSWORD}
      - TZ=Asia/Shanghai
    ports:
      - "36005:3306"
    expose:
      - "3306"
    volumes:
      - ./mysql-master-data:/var/lib/mysql
      - ./init-db-sql/iflow.sql:/docker-entrypoint-initdb.d/1-schema.sql
      - ./init-db-sql/init-master.sh:/docker-entrypoint-initdb.d/2-init-master.sh
    command: [
      "--log-bin=mysql-bin",
      "--server-id=${MASTER_SERVER_ID}",
      "--character-set-server=utf8mb4",
      "--collation-server=utf8mb4_unicode_ci",
      "--innodb_flush_log_at_trx_commit=1",
      "--sync_binlog=1"
      ]
 
  mysql-node-1: &mysql-node
    <<: *mysql
    container_name: mysql-iflow-node-1
    environment:
      - MYSQL_ROOT_PASSWORD=${NODE_MYSQL_ROOT_PASSWORD}
      - MASTER_MYSQL_ROOT_PASSWORD=${MASTER_MYSQL_ROOT_PASSWORD}
      - TZ=Asia/Shanghai
    ports:
      - "36006:3306"
    depends_on:
      - mysql-master
    volumes:
      - ./mysql-node-1-data:/var/lib/mysql
      - ./init-db-sql/iflow.sql:/docker-entrypoint-initdb.d/1-schema.sql
      - ./init-db-sql/init-node.sh:/docker-entrypoint-initdb.d/2-init-node.sh
    command: [
      "--server-id=${NODE_1_SERVER_ID}",
      "--character-set-server=utf8mb4",
      "--collation-server=utf8mb4_unicode_ci",
      ]
  
  mysql-node-2:
    <<: *mysql-node
    container_name: mysql-iflow-node-2
    environment:
      - MYSQL_ROOT_PASSWORD=${NODE_MYSQL_ROOT_PASSWORD}
      - MASTER_MYSQL_ROOT_PASSWORD=${MASTER_MYSQL_ROOT_PASSWORD}
      - TZ=Asia/Shanghai
    ports:
      - "36007:3306"
    volumes: 
      - ./mysql-node-2-data:/var/lib/mysql
      - ./init-db-sql/iflow.sql:/docker-entrypoint-initdb.d/1-schema.sql
      - ./init-db-sql/init-node.sh:/docker-entrypoint-initdb.d/2-init-node.sh
    command: [
      "--server-id=${NODE_2_SERVER_ID}",
      "--character-set-server=utf8mb4",
      "--collation-server=utf8mb4_unicode_ci",
      ]

```

## 操作说明

- 将database目录上传到满足docker-compose运行环境的服务器

- 在docker-compose目录下新建.env文件,.env文件默认不可见,可以用ls -a查看,.env文件包含数据库server_id，数据库密码和主从用户的配置

```
#image tag

TAG=5.7.22

MASTER_SERVER_ID=1
NODE_1_SERVER_ID=10
NODE_2_SERVER_ID=20

MASTER_MYSQL_ROOT_PASSWORD=123456
NODE_MYSQL_ROOT_PASSWORD=123456

MYSQL_REPLICATION_USER=repl
MYSQL_REPLICATION_PASSWORD=123456
```

- 根据需要修改完所有配置之后启动容器即可