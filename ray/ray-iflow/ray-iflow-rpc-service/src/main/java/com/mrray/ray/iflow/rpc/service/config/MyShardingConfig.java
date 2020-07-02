package com.mrray.ray.iflow.rpc.service.config;

import com.google.common.base.Preconditions;
import io.shardingsphere.core.exception.ShardingException;
import io.shardingsphere.shardingjdbc.api.MasterSlaveDataSourceFactory;
import io.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import io.shardingsphere.shardingjdbc.spring.boot.common.SpringBootConfigMapConfigurationProperties;
import io.shardingsphere.shardingjdbc.spring.boot.common.SpringBootPropertiesConfigurationProperties;
import io.shardingsphere.shardingjdbc.spring.boot.masterslave.SpringBootMasterSlaveRuleConfigurationProperties;
import io.shardingsphere.shardingjdbc.spring.boot.sharding.SpringBootShardingRuleConfigurationProperties;
import io.shardingsphere.shardingjdbc.spring.boot.util.PropertyUtil;
import io.shardingsphere.shardingjdbc.util.DataSourceUtil;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.beans.ConstructorProperties;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 自定义sharding-jdbc配置替换sharding-jdbc-spring-boot-starter中的SpringBootConfiguration
 *
 * @author lyc
 **/
@Configuration
@EnableConfigurationProperties({SpringBootShardingRuleConfigurationProperties.class, SpringBootMasterSlaveRuleConfigurationProperties.class, SpringBootConfigMapConfigurationProperties.class, SpringBootPropertiesConfigurationProperties.class})
public class MyShardingConfig implements EnvironmentAware {
    private final SpringBootShardingRuleConfigurationProperties shardingProperties;
    private final SpringBootMasterSlaveRuleConfigurationProperties masterSlaveProperties;
    private final SpringBootConfigMapConfigurationProperties configMapProperties;
    private final SpringBootPropertiesConfigurationProperties propMapProperties;
    private final Map<String, DataSource> dataSourceMap = new LinkedHashMap();

    @ConstructorProperties({"shardingProperties", "masterSlaveProperties", "configMapProperties", "propMapProperties"})
    public MyShardingConfig(SpringBootShardingRuleConfigurationProperties shardingProperties, SpringBootMasterSlaveRuleConfigurationProperties masterSlaveProperties, SpringBootConfigMapConfigurationProperties configMapProperties, SpringBootPropertiesConfigurationProperties propMapProperties) {
        this.shardingProperties = shardingProperties;
        this.masterSlaveProperties = masterSlaveProperties;
        this.configMapProperties = configMapProperties;
        this.propMapProperties = propMapProperties;
    }

    @Bean("shardingDataSource")
    @Primary
    public DataSource dataSource() throws SQLException {
        return null == masterSlaveProperties.getMasterDataSourceName() ? ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingProperties.getShardingRuleConfiguration(), configMapProperties.getConfigMap(), propMapProperties.getProps()) : MasterSlaveDataSourceFactory.createDataSource(dataSourceMap, masterSlaveProperties.getMasterSlaveRuleConfiguration(), configMapProperties.getConfigMap(), propMapProperties.getProps());
    }

    @Override
    public final void setEnvironment(Environment environment) {
        setDataSourceMap(environment);
    }

    private void setDataSourceMap(Environment environment) {
        String prefix = "sharding.jdbc.datasource.";
        String dataSources = environment.getProperty(prefix + "names");
        String[] dsArray = dataSources.split(",");
        int dsCount = dsArray.length;

        for (int i = 0; i < dsCount; ++i) {
            String each = dsArray[i];

            try {
                Map<String, Object> dataSourceProps = (Map) PropertyUtil.handle(environment, prefix + each.trim(), Map.class);
                Preconditions.checkState(!dataSourceProps.isEmpty(), "Wrong datasource properties!");
                DataSource dataSource = DataSourceUtil.getDataSource(dataSourceProps.get("type").toString(), dataSourceProps);
                dataSourceMap.put(each, dataSource);
            } catch (ReflectiveOperationException var10) {
                throw new ShardingException("Can't find datasource type!", var10);
            }
        }

    }
} 
