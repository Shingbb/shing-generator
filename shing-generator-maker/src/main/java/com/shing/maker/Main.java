package com.shing.maker;

import com.shing.maker.cli.CommandExecutor;
import freemarker.template.TemplateException;

import java.io.IOException;

/**
 * @author LBC
 */
public class Main {
    public static void main(String[] args) throws TemplateException, IOException, InterruptedException {
        CommandExecutor commandExecutor = new CommandExecutor();
        args = new String[]{"generate", "--needGit=false"};
        commandExecutor.doExecute(args);
    }
}