package com.shing.maker.model;

import lombok.Data;

/**
 * 静态模板配置
 *
 * @author LBC
 */
@Data
public class DataModel {
    //我们先明确几个动态生成的而求：
    //1.在代码开头增加作者@Author注祥（增加代码）
    //2,修改程序输出的信总提示（林换代码）
    //3,将循环读取输入改为单饮读取（可选代码）
    /**
     * 是否生成 .gitignore 文件
     */
    public boolean needGit = true;
    /**
     * 是否生成循环
     */
    private boolean loop =false;

    /**
     * 核心模板
     */
    public MainTemplate mainTemplate = new MainTemplate();


    /**
     * 用于生成核心模板文件
     */
    @Data
    public static class MainTemplate {

        /**
         * 作者注释
         */
        private String author = "shing";

        /**
         * 输出信息
         */
        private String outputText = "输出结果";
    }

}
