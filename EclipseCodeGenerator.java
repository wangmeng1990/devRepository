package *.generator;

import java.util.Scanner;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.google.common.base.Strings;

public class EclipseCodeGenerator {

	private static final String TYPE_DB = "db";
	private static final String TYPE_ENTITY = "entity";
	private static final String TYPE_INTERFACE = "interface";
	
	private static String url = "";
	private static String user = "";
	private static String pass = "";
	private static String driver = "com.mysql.jdbc.Driver";

	public static String scanner(String tip) {
		Scanner scanner = new Scanner(System.in);
		StringBuilder help = new StringBuilder();
		help.append("请输入" + tip + "：");
		System.out.println(help.toString());
		if (scanner.hasNext()) {
			String ipt = scanner.next();
			if (Strings.isNullOrEmpty(ipt) == false) {
				scanner.close();
				return ipt;
			}
		}
		scanner.close();
		throw new MybatisPlusException("请输入正确的" + tip + "！");
	}

	public static void main(String[] args) {
		String tableName = scanner("表名，多个英文逗号分割");
		generator(tableName, TYPE_DB);
		generator(tableName, TYPE_ENTITY);
		generator(tableName, TYPE_INTERFACE);
	}

	private static void generator(String tableName, String type) {
		// 代码生成器
		AutoGenerator mpg = new AutoGenerator();
		// 包配置
		PackageConfig pc = new PackageConfig();
		pc.setParent("com.yinuo.framework");
		pc.setService("api");
		// 包配置
		mpg.setPackageInfo(pc);
		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		String projectPath = System.getProperty("user.dir");
		if (TYPE_DB.equals(type)) {
			gc.setOutputDir(projectPath + "/src/main/java");
			// 是否覆盖已有文件
			gc.setFileOverride(true);
		}
		if (TYPE_ENTITY.equals(type)) {
			gc.setOutputDir(projectPath.replace("*-user", "*-pojo") + "/src/main/java");
		}
		if (TYPE_INTERFACE.equals(type)) {
			gc.setOutputDir(projectPath.replace("*-user", "*-interface") + "/src/main/java");
		}
		gc.setAuthor("wangm");
		gc.setOpen(false);

		// 开启二级缓存
		// gc.setEnableCache(true);

		gc.setBaseColumnList(true);
		gc.setBaseResultMap(true);
		// 实体属性 Swagger2 注解
		gc.setSwagger2(true);
		mpg.setGlobalConfig(gc);

		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setUrl(url);
		dsc.setDriverName(driver);
		dsc.setUsername(user);
		dsc.setPassword(pass);
		mpg.setDataSource(dsc);

		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		strategy.setSuperEntityClass("*.entity.BaseModel");
		strategy.setNaming(NamingStrategy.underline_to_camel);
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);
		strategy.setEntityLombokModel(true);
		strategy.setInclude(tableName.split(","));
		strategy.setSuperEntityColumns("id");
		strategy.setTablePrefix("t_");

		TemplateConfig templateConfig = new TemplateConfig();
		templateConfig.setController(null);
		if (TYPE_DB.equals(type)) {
			templateConfig.setService(null);
			templateConfig.setEntity(null);
		}
		if (TYPE_ENTITY.equals(type)) {
			templateConfig.setMapper(null);
			templateConfig.setXml(null);
			templateConfig.setService(null);
			templateConfig.setServiceImpl(null);
		}
		if (TYPE_INTERFACE.equals(type)) {
			templateConfig.setEntity(null);
			templateConfig.setMapper(null);
			templateConfig.setXml(null);
			templateConfig.setServiceImpl(null);
		}

		mpg.setTemplate(templateConfig);
		mpg.setStrategy(strategy);
		mpg.setTemplateEngine(new FreemarkerTemplateEngine());
		mpg.execute();
	}
}
