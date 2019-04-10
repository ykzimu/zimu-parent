package com.zimu;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.zimu.common.mybatisplus.engine.ZimuFreemarkerTemplateEngine;
import com.zimu.entity.base.BaseEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CodeGenerator {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/zimu/src/main/java");
        gc.setAuthor("杨坤");
        gc.setOpen(false);
        gc.setFileOverride(false);
        gc.setEntityName("%sEntity");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        //gc.setXmlName("%sEntityMapper");
        //gc.setMapperName("%sEntityMapper");
        // gc.setBaseColumnList(true);
        // gc.setBaseResultMap(true);
        gc.setDateType(DateType.TIME_PACK);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://mysql.qinbeixian.com:3306/zimu?useUnicode=true&characterEncoding=UTF-8&useSSL=false");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("yangkun");
        dsc.setPassword("YKsyc@123");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        //pc.setModuleName("admin");
        pc.setParent("com.zimu");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名
                return projectPath + "/zimu/src/main/java/com/zimu/dao/mapper/" + tableInfo.getEntityName().substring(0, tableInfo.getEntityName().length() - 6) + "Mapper" + StringPool.DOT_XML;
            }
        });


        // cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();

        templateConfig.setController(null);
        //templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //strategy.setSuperEntityClass("com.baomidou.ant.common.BaseEntity");
        strategy.setEntityLombokModel(true);
        //strategy.setRestControllerStyle(true);
        //strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");
        strategy.setInclude("^t_.*");
        // strategy.setSuperEntityColumns("id");
        strategy.setControllerMappingHyphenStyle(false);
        strategy.setTablePrefix("t_");
        strategy.setEntityTableFieldAnnotationEnable(true);
        strategy.setVersionFieldName("version");
        strategy.setSuperEntityClass(BaseEntity.class);
        strategy.setSuperEntityColumns("id", "del_flag", "create_by", "create_date", "update_by", "update_date", "version");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new ZimuFreemarkerTemplateEngine());
        mpg.execute();
    }

}
