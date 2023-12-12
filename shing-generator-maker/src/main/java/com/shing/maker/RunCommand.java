package com.shing.maker;

import com.shing.maker.cli.CommandExecutor;

/**
 * @author LBC
 */
public class RunCommand {
    public static void main(String[] args) {
//        args = new String[]{"generate", "-l", "-a", "-o"};
//        args = new String[]{"config"};
//        args = new String[]{"list"};
        CommandExecutor commandExecutor = new CommandExecutor();
        commandExecutor.doExecute(args);
    }
}