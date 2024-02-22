package com.shing.maker.generator.main;

import freemarker.template.TemplateException;

import java.io.IOException;

/**
 * 生成代码生成器
 * @author shing
 */
public class MainGenerator  extends  GenerateTemplate {
    @Override
    protected void buildDish(String outputPath, String sourceCopyDestPath, String jarPath, String shellOutputFilePath) throws TemplateException, IOException, InterruptedException {
        System.out.println("不要给我输出 dist 啦！");

    }

    public static void main(String[] args) throws TemplateException, IOException, InterruptedException {
        /* super.buildDish(outputPath, sourceCopyDestPath, jarPath, shellOutputFilePath);*/
        MainGenerator mainGenerator = new MainGenerator();
        mainGenerator.doGenerate();
    }
}
