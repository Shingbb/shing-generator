package com.shing.maker.generator;

import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

/**
 * @author shing
 */
public class ScriptGenerator {

    // 生成脚本文件
    public static void doGenerate(String outputPath, String jarPath) throws IOException {
        // 直接写入脚本文件

        // linux
        StringBuilder sb = new StringBuilder();
        // Add the shebang line
        sb.append("#!/bin/bash").append("\n");
        // Add the command to execute the jar
        sb.append(String.format("java -jar %s \"$@\"", jarPath)).append("\n");
        // Write the file to the given path
        FileUtil.writeBytes(sb.toString().getBytes(StandardCharsets.UTF_8), outputPath);
        // 添加可执行权限
        try {
            //Create a set of PosixFilePermission objects from a string of permissions
            Set<PosixFilePermission> permissions = PosixFilePermissions.fromString("rwxrwxrwx");
            //Set the permissions of a file at a given path to the given permissions
            Files.setPosixFilePermissions(Paths.get(outputPath), permissions);
        } catch (Exception e) {

        }

        // windows
        sb = new StringBuilder();
        //Create a batch file to run the jar
        sb.append("@echo off").append("\n");
        //Append the java command to the batch file
        sb.append(String.format("java -jar %s %%*", jarPath)).append("\n");
        //Write the batch file to the specified output path
        FileUtil.writeBytes(sb.toString().getBytes(StandardCharsets.UTF_8), outputPath + ".bat");


    }

    public static void main(String[] args) throws IOException {
        // Get the current working directory from the system properties
        String outputPath = System.getProperty("user.dir") + File.separator + "generator";
        // Call the doGenerate method with the output path and an empty string
        doGenerate(outputPath, "");
    }
}