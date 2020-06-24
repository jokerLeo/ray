package com.mrray.ray.iflow.dao;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.mrray.ray.common.YmlUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 代码生成器
 *
 * @author lyc
 **/
public class CodeGenerator {
    private static String MODULE = "/ray-iflow";
    private static String BASE_PACKAGE = "com.mrray.ray.iflow";
    private static String URL = YmlUtils.getYmlByFileName("generator.yml").get("generator.jdbc.url");
    private static String DRIVER = YmlUtils.getYmlByFileName("generator.yml").get("generator.jdbc.driver");
    private static String USERNAME = YmlUtils.getYmlByFileName("generator.yml").get("generator.jdbc.username");
    private static String PASSWORD = YmlUtils.getYmlByFileName("generator.yml").get("generator.jdbc.password");

    public CodeGenerator() {
    }

    private static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入" + tip + "：");
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }

        throw new MybatisPlusException("请输入正确的" + tip + ": ");
    }

    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();
        GlobalConfig gc = new GlobalConfig();
        //主module路径
        final String projectPath = System.getProperty("user.dir") + MODULE;
        gc.setAuthor("lyc");
        gc.setOpen(false);
        mpg.setGlobalConfig(gc);
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(URL);
        dsc.setDriverName(DRIVER);
        dsc.setUsername(USERNAME);
        dsc.setPassword(PASSWORD);
        mpg.setDataSource(dsc);
        final PackageConfig pc = new PackageConfig();
        String modelName = scanner("模块名").trim();
        //分布式生成避免包重合
        pc.setModuleName(null);
        pc.setParent(null);
        mpg.setPackageInfo(pc);
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
            }
        };
        List<FileOutConfig> focList = new ArrayList();
        //自定义entity输出路径
        String entityPath = "/templates/entity.java.ftl";
        pc.setEntity(BASE_PACKAGE + ".dao." + modelName + ".model");
        focList.add(new FileOutConfig(entityPath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + MODULE + "-dao" + "/src/main/java/" +
                        BASE_PACKAGE.replaceAll("\\.", "/") + "/dao/" +
                        modelName + "/model/" + tableInfo.getEntityName() + StringPool.DOT_JAVA;
            }
        });
        //自定义mapper输出路径
        String mapperPath = "/templates/mapper.java.ftl";
        pc.setMapper(BASE_PACKAGE + ".dao." + modelName + ".mapper");
        focList.add(new FileOutConfig(mapperPath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + MODULE + "-dao" + "/src/main/java/" +
                        BASE_PACKAGE.replaceAll("\\.", "/") + "/dao/" +
                        modelName + "/mapper/" + tableInfo.getMapperName() + StringPool.DOT_JAVA;
            }
        });
        //自定义service输出路径
        String servicePath = "/templates/service.java.ftl";
        pc.setService(BASE_PACKAGE + ".rpc.api." + modelName);
        focList.add(new FileOutConfig(servicePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + MODULE + "-rpc-api" + "/src/main/java/" +
                        BASE_PACKAGE.replaceAll("\\.", "/") + "/rpc/api/"
                        + modelName + "/" + tableInfo.getServiceName() + StringPool.DOT_JAVA;
            }
        });
        //自定义serviceImpl输出路径
        String serviceImplPath = "/templates/serviceImpl.java.ftl";
        pc.setServiceImpl(BASE_PACKAGE + ".rpc.service.impl." + modelName);
        focList.add(new FileOutConfig(serviceImplPath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + MODULE + "-rpc-service" + "/src/main/java/" +
                        BASE_PACKAGE.replaceAll("\\.", "/") + "/rpc/service/impl/"
                        + modelName + "/" + tableInfo.getServiceImplName() + StringPool.DOT_JAVA;
            }
        });
        //自定义mapper.xml输出路径
        String mapperXmlPath = "/templates/mapper.xml.ftl";
        focList.add(new FileOutConfig(mapperXmlPath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + MODULE + "-rpc-service" + "/src/main/resources/mappers/" +
                        modelName + "/" + tableInfo.getMapperName() + StringPool.DOT_XML;
            }
        });
        //自定义controller输出路径
        String controllerPath = "/templates/controller.java.ftl";
        pc.setController(BASE_PACKAGE + ".admin.controller." + modelName);
        focList.add(new FileOutConfig(controllerPath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + MODULE + "-admin" + "/src/main/java/" +
                        BASE_PACKAGE.replaceAll("\\.", "/") + "/admin/controller/" +
                        modelName + "/" + tableInfo.getControllerName() + StringPool.DOT_JAVA;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        TemplateConfig templateConfig = new TemplateConfig();
        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        //目录是resources下的相对路径,若不自定义模板则需注释以下内容
        templateConfig.setMapper("templates/mapper.java");
        templateConfig.setService("templates/service.java");
        templateConfig.setController("templates/controller.java");
        templateConfig.setServiceImpl("templates/serviceImpl.java");
        templateConfig.setEntity("templates/entity.java");
        mpg.setTemplate(templateConfig);
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        //不生成父类属性
        //strategy.setSuperEntityColumns(new String[]{"id"});
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(new String[]{modelName + "_"});
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}