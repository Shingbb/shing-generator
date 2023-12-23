package com.shing.maker.meta;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

/**
 * 元信息校验
 *
 * @author LBC
 */
public class MetaValidator {

    public static void doaValidatorAndFill(Meta meta) {

        validAndFillMetaRoot(meta);

        validAndFillConfig(meta);

        validAndFillModelConfig(meta);


    }

    // modelConfig 校验和默认值
    private static void validAndFillModelConfig(Meta meta) {
        // modelConfig 校验和默认值
        Meta.ModelConfig modelConfig = meta.getModelConfig();
        if (modelConfig != null) {
            List<Meta.ModelConfig.ModelInfo> modelInfoList = modelConfig.getModels();
            if (CollUtil.isNotEmpty(modelInfoList)){
                for (Meta.ModelConfig.ModelInfo modelInfo : modelInfoList){
                    String fieldName = modelInfo.getFieldName();
                    if (StrUtil.isBlank(fieldName)){
                        throw new MetaException("未填写 fieldName");
                    }
                    String modelInfoType = modelInfo.getType();
                    if (StrUtil.isEmpty(modelInfoType)){
                        modelInfo.setType("String");
                    }

                }
            }

        }
    }

    // fileConfig 校验和默认值
    private static void validAndFillConfig(Meta meta) {
        // fileConfig 校验和默认值
        Meta.FileConfig fileConfig = meta.getFileConfig();
        if (fileConfig != null) {
            // sourceRootPath 必填
            String sourceRootPath = fileConfig.getSourceRootPath();
            if (StrUtil.isBlank(sourceRootPath)) {
                throw new MetaException("未填写 sourceRootPath");
            }
            // inputRootPath:.source+sourceRootPath的最后一个层级路径
            String inputRootPath = fileConfig.getInputRootPath();
            String defaultInputRootPath = ".source" + File.separator +
                    FileUtil.getLastPathEle(Paths.get(sourceRootPath)).getFileName().toString();
            if (StrUtil.isEmpty(inputRootPath)) {
                fileConfig.setInputRootPath(defaultInputRootPath);
            }
            // outputRootPath: 默认为当前路径下的 generated
            String outputRootPath = fileConfig.getOutputRootPath();
            String defaultOutputRootPath = "generated";
            if (StrUtil.isEmpty(outputRootPath)) {
                fileConfig.setOutputRootPath(defaultOutputRootPath);
            }

            String fileConfigType = fileConfig.getType();
            String defaultType = "dir";
            if (StrUtil.isEmpty(fileConfigType)) {
                fileConfig.setType(defaultType);
            }

            List<Meta.FileConfig.FileInfo> fileInfoList = fileConfig.getFiles();
            if (CollUtil.isNotEmpty(fileInfoList)) {
                for (Meta.FileConfig.FileInfo fileInfo : fileInfoList) {
                    // inputPath 必填
                    String inputPath = fileInfo.getInputPath();
                    if(StrUtil.isBlank(inputPath)){
                        throw  new MetaException("未填写 inputPath");
                    }
                    // outputPath: 默认等于 inputPath
                    String outputPath = fileInfo.getOutputPath();
                    if(StrUtil.isEmpty(outputPath)) {
                        fileInfo.setOutputPath(inputPath);
                    }
                    // type: 默认 inputPath 有文件后缀（比如 .java）默认为 file， 否则就是 dir
                    String type = fileInfo.getType();
                    if(StrUtil.isBlank (type)){
                        // 无文件后缀
                        if(StrUtil.isBlank(FileUtil.getSuffix(inputPath))){
                            fileInfo.setType("dir");
                        }else {
                            fileInfo.setType("file");
                        }
                    }
                    // generateType: 文件结尾部位 ftl ，generateType 为 static, 否则为 dynamic
                    String generateType = fileInfo.getGenerateType();
                    if (StrUtil.isBlank(generateType)){
                        if (inputPath.endsWith(".ftl")){
                            fileInfo.setGenerateType("dynamic");

                        }
                    }

                }

            }

        }
    }

    // 基础信息校验和默认值
    private static void validAndFillMetaRoot(Meta meta) {
        // 基础信息校验和默认值
        String name = meta.getName();
        if (StrUtil.isBlank(name)) {
            name = "my-generator";
            meta.setName(name);
        }
        String description = meta.getDescription();
        if (StrUtil.isEmpty(description)) {
            description = "我的模板代码生成器";
            meta.setDescription(description);
        }
        String basePackage = meta.getBasePackage();
        if (StrUtil.isBlank(basePackage)) {
            basePackage = "com.shing";
            meta.setBasePackage(basePackage);
        }
        String version = meta.getVersion();
        if (StrUtil.isEmpty(version)) {
            version = "1.0";
            meta.setName(version);
        }
        String author = meta.getAuthor();
        if (StrUtil.isEmpty(description)) {
            author = "shing";
            meta.setName(author);
        }
        String createTime = meta.getCreateTime();
        if (StrUtil.isEmpty(createTime)) {
            createTime = DateUtil.now();
            meta.setName(createTime);
        }
    }


}
