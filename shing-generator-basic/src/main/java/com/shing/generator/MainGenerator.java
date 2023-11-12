package com.shing.generator;

import com.shing.model.MainTemplateConfig;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * @author LBC
 * 核心生成器
 */
public class MainGenerator {

    /**
     * 生成
     *
     * @param model 数据模型
     * @throws TemplateException
     * @throws IOException
     */
    public static void doGenerate(Object model) throws TemplateException, IOException {

        // 1.静态文件生成
        String projectPath = System.getProperty("user.dir");
        System.out.println(projectPath);
        // 输入路径 --> projectPath="F:\Project\shing-generator"
        String inputPath = projectPath + File.separator + "shing-generator-demo-projects" + File.separator + "acm-template";
        // 输出路径
        String outputPath = projectPath + File.separator;
        //复制--生成静态文件
        StaticGenerator.copyFilesByHutool(inputPath, outputPath);
        //生成动态
        String dynamicInputPath = projectPath + File.separator + "shing-generator-basic" + File.separator + "src/main/resources/templates/MainTemplate.java.ftl";
        String dynamicOutputPath = projectPath + File.separator + "acm-template/src/com/shing/acm//MainTemplate.java";
        System.out.println(dynamicInputPath);
        System.out.println(dynamicOutputPath);
    }

    public static void main(String[] args) throws TemplateException, IOException {
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("shing");
        mainTemplateConfig.setLoop(false);
        mainTemplateConfig.setOutputText("输出求和结果：");
        doGenerate(mainTemplateConfig);
    }

}
