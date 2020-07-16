package com.jtexplorer.create.code;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CreateCode {
    public static void main(String[] args) {
        // 代码生成类
        AutoGenerator mpg = new AutoGenerator();
        // 配置模板，用于定义模板类型和设置模板地址（模板文件）
        TemplateConfig templateConfig = new TemplateConfig();
        // 配置模板地址注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别，注意的是这些配置如果不做配置，则会自动寻找以下地址（这些地址的模板是mybatis-plus-generator包中自带的）
        // 注意/templates/指的位置是项目中的resources文件夹下的templates文件夹
        // 注意，如果想要使用InjectionConfig来自定义生成文件的名称，则必须将对应的set方法设成null，如mapper.xml想要自定义，则必须增加语句templateConfig.setXml(null);否则会出错
//        templateConfig.setMapper("/templates/mapper.java");
//        templateConfig.setEntity(/templates/entity.java);
//        templateConfig.setService("/templates/service.java");
//        templateConfig.setServiceImpl("/templates/serviceImpl.java");
//        templateConfig.setController("/templates/controller.java");
//        templateConfig.setXml("/templates/mapper.xml");
        mpg.setTemplate(templateConfig);
        // 设置模板类型是freemarker
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        // 获取项目地址：到项目最上层地址，比如该项目会到/CodeStandard
        String projectPath = System.getProperty("user.dir");


        // 设置公用配置
        GlobalConfig globalConfig = new GlobalConfig();
        // 设置文件输出地址
        globalConfig.setOutputDir(projectPath + "/Main/src/main/java");
        // 设置作者名称，用于类文件中的类注释的 @author 标签
        globalConfig.setAuthor("苏空城");
        // 未明
        globalConfig.setOpen(false);
        // 设置xml中是否生成<resultMap id="BaseResultMap" type="com.jtexplorer.entity.Exam">标签
        globalConfig.setBaseResultMap(true);
        // 设置xml中是否生成<sql id="Base_Column_List">标签
        globalConfig.setBaseColumnList(true);

        mpg.setGlobalConfig(globalConfig);

        // 设置数据库连接信息
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl("jdbc:mysql://192.168.123.168:3306/arts_see?useSSL=false&serverTimezone=Hongkong&useUnicode=true&characterEncoding=utf-8&allowPublicKeyRetrieval=true");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("sandong2012");
        mpg.setDataSource(dataSourceConfig);

        // 设置各class文件输出包
        PackageConfig pc = new PackageConfig();
        // 设置父包，下面设置的其他包将自动加上该项设置
        pc.setParent("com.jtexplorer");
        // controller类的包，会自动加上com.jtexplorer
        pc.setController("controller.admin");
        // entity类的包，会自动加上com.jtexplorer
        pc.setEntity("entity");
        // mapper类的包，会自动加上com.jtexplorer
        pc.setMapper("mapper");
        // service类的包，会自动加上com.jtexplorer
        pc.setService("service");
        // service.impl类的包，会自动加上com.jtexplorer
        pc.setServiceImpl("service.impl");
        // 注意，因为xml要生成的地址不在java（globalConfig.setOutputDir的设置）文件中，因此在这里无法设置，需要使用InjectionConfig单独设置
        mpg.setPackageInfo(pc);

        // 自定义配置，用于设置需要单独设置输出路径和名称有特殊前后缀的类
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
            }
        };
        // 自定义配置列表，在初始化FileOutConfig的方法中可以进行TableInfo（模板中${table.xx}中的table）信息的获取和修改
        List<FileOutConfig> focList = new ArrayList<>();
        // 首先是controller，这里的参数值是自己做的模板文件
        focList.add(new FileOutConfig("/templates/controllerMy.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 获取列信息
                List<TableField> tableFields = tableInfo.getFields();
                // 由于mybatis中的datetime类的列会自动设置成LocalDateTime类型，但是这个类型在查询时会出现java.lang.AbstractMethodError: Method org/apache/commons/dbcp/DelegatingResultSet.getObject(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object; is abstract
                // 因此，这里将LocalDateTime类型改为Date类型
                tableFields.forEach(v->{
                    if("LocalDateTime".equals(v.getPropertyType())){
                        Set<String> packages = tableInfo.getImportPackages();
                        packages.remove("java.time.LocalDateTime");
                        IColumnType iColumnType = new IColumnType() {
                            @Override
                            public String getType() {
                                return "Date";
                            }

                            @Override
                            public String getPkg() {
                                return "java.util.Date";
                            }
                        };
                        v.setColumnType(iColumnType);
                    }
                });
                // 重新将列数据导入该类用于重新设置需要导入的包
                tableInfo.setFields(tableFields);
                // 设置controller类的类名的前后缀
                tableInfo.setControllerName("Admin" + tableInfo.getEntityName() + "Controller");
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/Main/src/main/java/com/jtexplorer/controller/admin/" + tableInfo.getControllerName() + StringPool.DOT_JAVA;
            }
        });
        // 设置了controller的自定义配置之后，就必须将配置类中的该项设置为null，即使前文没有设置该项，也不可遗漏，否则，将还会生成一个默认名称的类
        templateConfig.setController(null);
        // 然后是mapper.xml，这里的配置值是默认的模板文件，该文件在自己的项目文件夹下没有，但是运行之后，在target文件夹下是存在的（是引的包中自带的）
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/Main/src/main/resources/sqlmap/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        templateConfig.setXml(null);

        // 将列表放进InjectionConfig类对象中
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // 未明
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 未明
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // 未明
        strategy.setEntityLombokModel(true);
        // 未明
        strategy.setRestControllerStyle(true);
        // 表名，多个表的话可以使用英文逗号隔开，如：strategy.setInclude("exam","user");
        strategy.setInclude("exam");
        // 未明
        strategy.setControllerMappingHyphenStyle(true);
        mpg.setStrategy(strategy);
        // 运行
        mpg.execute();
    }
}
