package com.shing.maker.generator.main;

import freemarker.template.TemplateException;

import java.io.IOException;

/**
 * 生成代码生成器压缩包
 * @author shing
 */
public class ZipGenerator extends  GenerateTemplate {
    @Override
    protected String buildDist(String outputPath, String sourceCopyDestPath, String jarPath, String shellOutputFilePath) throws TemplateException, IOException, InterruptedException {
        String distPath = super.buildDist(outputPath, sourceCopyDestPath, jarPath, shellOutputFilePath);
        return super.buildZip(distPath);
    }

}
