package com.shing.generator;

import com.shing.model.MainTemplateConfig;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * @author LBC
 * 动态文件生成
 */
public class DynamicGenerator {
    public static void main(String[] args) throws IOException, TemplateException {
        /* //获取路径
        File file = new File("src/main/resources/templates");
        String projectPath = System.getProperty("user.dir");
        System.out.println(projectPath);
        System.out.println(file.getAbsolutePath());*/
        /* // 如果最外层是shing-generator
        String projectPath = System.getProperty("user.dir") + File.separator +"shing-generator-basic";
        File parentFile = new File(projectPath);
        File file =new File(parentFile, "src/main/resources/templates");
        //指定模板文件所在的路径
        configuration.setDirectoryForTemplateLoading(file);*/
        /*
        // new 出 Configuration 对象，参数为 FreeMarker 版本号
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);
        // 指定模板文件所在的路径
        configuration.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));

        // 设置模板文件使用的字符集
        configuration.setDefaultEncoding("utf-8");

        //日期格式
        configuration.setNumberFormat("0.######");

        // 创建模板对象，加载指定模板
        Template template = configuration.getTemplate("MainTemplate.java.ftl");

        // 创建数据模型
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("shing");
        mainTemplateConfig.setOutputText("输出结果");
        mainTemplateConfig.setLoop(false);

        //指定生成文件所在的路径,生成文件
        Writer out = new FileWriter("src/main/resources/MainTemplate.java");

        template.process(mainTemplateConfig, out);

        // 生成文件后别忘了关闭哦
        out.close();*/
        String projectPath = System.getProperty("user.dir");
        System.out.println(projectPath);
        // 输入路径 --> projectPath="F:\Project\shing-generator\shing-generator-basic"
        String inputPath = projectPath + File.separator + "src/main/resources/templates/MainTemplate.java.ftl";
        // 输出路径
        String outputPath = projectPath + File.separator + "src/main/resources/MainTemplate.java";

        System.out.println(inputPath);
        System.out.println(outputPath);

        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("shing");
        mainTemplateConfig.setLoop(false);
        mainTemplateConfig.setOutputText("求和结果：");
        doGenerate(inputPath, outputPath, mainTemplateConfig);
    }


    /**
     * 生成文件
     *
     * @param inputPath  模板文件输入路径
     * @param outputPath 输出路径
     * @param model      数据模型
     * @throws IOException
     * @throws TemplateException
     */
    public static void doGenerate(String inputPath, String outputPath, Object model) throws IOException, TemplateException {
        // new 出 Configuration 对象，参数为 FreeMarker 版本号
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);

        // 指定模板文件所在的路径
        File templateDir = new File(inputPath).getParentFile();
        configuration.setDirectoryForTemplateLoading(templateDir);

        // 设置模板文件使用的字符集
        configuration.setDefaultEncoding("utf-8");

        // 创建模板对象，加载指定模板
        String templateName = new File(inputPath).getName();
        Template template = configuration.getTemplate(templateName);

        // 创建数据模型
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("shing");
        mainTemplateConfig.setLoop(false);
        mainTemplateConfig.setOutputText("求和结果：");

        // 生成
        Writer out = new FileWriter(outputPath);
        template.process(model, out);

        // 生成文件后别忘了关闭哦
        out.close();
    }
}
