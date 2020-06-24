package com.cbr.study.batisplusdemo.util;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * 代码生成器，可以指定表生成
 * 输出路径需要指向工程路径 gc.setOutputDir
 *
 */
public class MysqlGenerator {

    public static void main(String[] args) {

        //创建代码生成器
        AutoGenerator mpg = new AutoGenerator();
        //指定模板引擎  默认velocity
        //mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        //全局配置
        GlobalConfig gc = new GlobalConfig();
        //生成完成后不弹出文件框
        gc.setOpen(false);
        //这里是工程路径，取到是vueStudy，所以有偏差 //得到当前项目的路径
        String projectPath = System.getProperty("user.dir");
        //生成文件输出根目录
        //gc.setOutputDir("F:\\CBR\\developer\\ideal\\mybatis-plus\\batisplusdemo\\src\\main\\java");
        gc.setOutputDir("H:\\DFJX\\code\\local\\mygithup\\esStudy\\batisplusdemo\\src\\main\\java");

        //是否覆盖已有文件
        gc.setFileOverride(true);
        //XML是否需要BaseResultMap
        gc.setBaseResultMap(true);
        //XML是否显示字段   XML columList
        gc.setBaseColumnList(true);
        // 作者
        gc.setAuthor("bingo");
        gc.setIdType(IdType.AUTO);
        // XML 二级缓存
        gc.setEnableCache(false);
        // 不需要ActiveRecord特性的请改为false
        gc.setActiveRecord(false);

        // 自定义文件命名，注意 %s 会自动填充表实体属性！【生成的文件名后缀】
        gc.setControllerName("%sController");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setMapperName("%sDao");
        gc.setXmlName("%sDao");
        mpg.setGlobalConfig(gc);

        //数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUrl("jdbc:mysql://localhost:3306/cbr_test?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=true");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        mpg.setDataSource(dsc);

        //策略配置
        StrategyConfig sc = new StrategyConfig();
        //TODO 表名前缀
        sc.setTablePrefix("sys_");
        //表名生成策略
        sc.setNaming(NamingStrategy.underline_to_camel);
        // 需要生成的表--表名，没有指定则生成全部表
        sc.setInclude(new String[] { "sys_menu" });
//        sc.setSuperServiceClass(null);
//        sc.setSuperServiceImplClass(null);
        //传入nul代表没有继承,可以传入
        sc.setSuperMapperClass("com.cbr.study.batisplusdemo.util.BaseDao");

        sc.setEntityBuilderModel(true);
        sc.setEntityLombokModel(true);
        mpg.setStrategy(sc);

        //包配置----【设置生成代码放在哪个包】
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.cbr.study.batisplusdemo");
        pc.setEntity("entity");
        pc.setController("controller");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setMapper("dao");
        pc.setXml("mapper");
        mpg.setPackageInfo(pc);

        // 执行生成
        mpg.execute();

    }

}
