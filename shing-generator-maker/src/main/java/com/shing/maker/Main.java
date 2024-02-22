package com.shing.maker;

import com.shing.maker.generator.main.MainGenerator;
import freemarker.template.TemplateException;

import java.io.IOException;

/**
 * author shing
 */
public class Main {
    public static void main(String[] args) throws TemplateException, IOException, InterruptedException {
        MainGenerator mainGenerator=new MainGenerator();
        mainGenerator.doGenerate();
    }
}
