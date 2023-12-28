package com.shing.maker.generator.main;

/**
 * @author LBC
 */
public class MainGenerator  extends  GenerateTemplate {
    @Override
    protected void buildDish(String outputPath, String sourceCopyDestPath, String jarPath, String shellOutputFilePath) {
        System.out.println("不要给我输出 dist 啦！");

//        super.buildDish(outputPath, sourceCopyDestPath, jarPath, shellOutputFilePath);
    }

    /*public static void main(String[] args) throws TemplateException, IOException, InterruptedException {
        MainGenerator mainGenerator=new MainGenerator();
        mainGenerator.doGenerate();
    }*/
}
