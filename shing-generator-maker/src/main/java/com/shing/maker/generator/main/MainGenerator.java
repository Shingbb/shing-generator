package com.shing.maker.generator.main;

/**
 * 生成代码生成器
 *
 * @author shing
 */
public class MainGenerator extends GenerateTemplate {

    @Override
    protected String buildDist(String outputPath, String sourceCopyDestPath, String jarPath, String shellOutputFilePath) {
        System.out.println("不要给我输出 dist 啦！");
        return "";
    }
}
