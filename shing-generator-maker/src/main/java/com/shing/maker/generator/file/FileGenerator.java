package com.shing.maker.generator.file;

import com.shing.maker.model.DataModel;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * @author LBC
 * 核心生成器
 */
public class FileGenerator {

    /**
     * 生成
     *
     * @param model 数据模型
     * @throws TemplateException
     * @throws IOException
     */
    public static void doGenerate(Object model) throws TemplateException, IOException {
        // 在shing-generator下运行
        /*// 1.静态文件生成
        String projectPath = System.getProperty("user.dir");

        // 输入路径 --> projectPath="F:\Project\shing-generator"
        String inputPath = projectPath + File.separator + "shing-generator-demo-projects" + File.separator + "acm-template";
        // 输出路径
        String outputPath = projectPath + File.separator;
        //复制--生成静态文件
        StaticGenerator.copyFilesByHutool(inputPath, outputPath);
        //生成动态
        String dynamicInputPath = projectPath + File.separator + "shing-generator-basic" + File.separator + "src/main/resources/templates/MainTemplate.java.ftl";
        String dynamicOutputPath = projectPath + File.separator + "Acm-template/src/com/shing/acm/MainTemplate.java";
        // 执行生成
        DynamicGenerator.doGenerate(dynamicInputPath, dynamicOutputPath, model);

        System.out.println(projectPath);
        System.out.println(dynamicInputPath);
        System.out.println(dynamicOutputPath);*/

        // 在shing-generator/shing-generator-basic下运行
        String projectPath = System.getProperty("user.dir");
        // 整个项目的根路径
        File parentFile = new File(projectPath).getParentFile();
        // 输入路径
        String inputPath = new File(parentFile, "shing-generator-demo-projects/acm-template").getAbsolutePath();
        String outputPath = projectPath;
        // 生成静态文件
        StaticFileGenerator.copyFilesByHutool(inputPath, outputPath);
        // 生成动态文件
        String inputDynamicFilePath = projectPath + File.separator + "src/main/resources/templates/MainTemplate.java.ftl";
        String outputDynamicFilePath = outputPath + File.separator + "acm-template/src/com/shing/acm/MainTemplate.java";
        DynamicFileGenerator.doGenerate(inputDynamicFilePath, outputDynamicFilePath, model);
//        System.out.println(projectPath);
//        System.out.println(inputDynamicFilePath);
//        System.out.println(outputDynamicFilePath);

    }

    public static void main(String[] args) throws TemplateException, IOException {
        DataModel dataModel = new DataModel();
        dataModel.mainTemplate.setAuthor("shing");
        dataModel.setLoop(false);
        dataModel.mainTemplate.setOutputText("输出求和结果：");
        doGenerate(dataModel);
    }

}
