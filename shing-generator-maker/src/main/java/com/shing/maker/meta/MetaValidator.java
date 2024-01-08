package com.shing.maker.meta;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.shing.maker.meta.enums.FileGenerateTypeEnum;
import com.shing.maker.meta.enums.FileTypeEnum;
import com.shing.maker.meta.enums.ModelTypeEnum;

import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

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
        if (modelConfig == null) {
            return;
        }
        // modelConfig 默认值
        List<Meta.ModelConfig.ModelInfo> modelInfoList = modelConfig.getModels();
        if (!CollUtil.isNotEmpty(modelInfoList)) {
            return;
        }
        for (Meta.ModelConfig.ModelInfo modelInfo : modelInfoList) {
            // 为 group ,不校验
            String groupKey = modelInfo.getGroupKey();
            if (StrUtil.isNotBlank(groupKey)) {
                // 生成中间参数 “--author,”"--outputText"
                List<Meta.ModelConfig.ModelInfo> subModelInfoList = modelInfo.getModels();
                String allArgsStr = modelInfo.getModels().stream()
                        .map(subModelInfo -> String.format("\"--%s\"", subModelInfo.getFieldName()))
                        .collect(Collectors.joining(", "));
                modelInfo.setAllArgsStr(allArgsStr);
                continue;
            }
            // 输出路径默认值
            String fieldName = modelInfo.getFieldName();
            if (StrUtil.isBlank(fieldName)) {
                throw new MetaException("未填写 fieldName");
            }
            String modelInfoType = modelInfo.getType();
            if (StrUtil.isEmpty(modelInfoType)) {
                modelInfo.setType(ModelTypeEnum.STRING.getValue());
            }

        }

    }

    // fileConfig 校验和默认值
    private static void validAndFillConfig(Meta meta) {
        // fileConfig 校验和默认值
        Meta.FileConfig fileConfig = meta.getFileConfig();
        if (fileConfig == null) {
            return;
        }
        // sourceRootPath 必填
        String sourceRootPath = fileConfig.getSourceRootPath();
        if (StrUtil.isBlank(sourceRootPath)) {
            throw new MetaException("未填写 sourceRootPath");
        }
        // inputRootPath:.source+sourceRootPath的最后一个层级路径
        String inputRootPath = fileConfig.getInputRootPath();
        String defaultInputRootPath = ".source/" +
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
        String defaultType = FileTypeEnum.DIR.getValue();

        if (StrUtil.isEmpty(fileConfigType)) {
            fileConfig.setType(defaultType);
        }
        // fileInfo 默认值
        List<Meta.FileConfig.FileInfo> fileInfoList = fileConfig.getFiles();
        if (!CollUtil.isNotEmpty(fileInfoList)) {
            return;
        }
        for (Meta.FileConfig.FileInfo fileInfo : fileInfoList) {
            String type = fileInfo.getType();
            if (FileTypeEnum.GROUP.getValue().equals(type)) {
                continue;
            }
            // inputPath 必填
            String inputPath = fileInfo.getInputPath();
            if (StrUtil.isBlank(inputPath)) {
                throw new MetaException("未填写 inputPath");
            }
            // outputPath: 默认等于 inputPath
            String outputPath = fileInfo.getOutputPath();
            if (StrUtil.isEmpty(outputPath)) {
                fileInfo.setOutputPath(inputPath);
            }
            // type: 默认 inputPath 有文件后缀（比如 .java）默认为 file， 否则就是 dir

            if (StrUtil.isBlank(type)) {
                // 无文件后缀
                if (StrUtil.isBlank(FileUtil.getSuffix(inputPath))) {
                    fileInfo.setType(FileTypeEnum.DIR.getValue());
                } else {
                    fileInfo.setType(FileTypeEnum.FILE.getValue());
                }
            }
            // generateType: 文件结尾部位不为 ftl ，generateType 为 static, 否则为 dynamic
            String generateType = fileInfo.getGenerateType();
            if (StrUtil.isBlank(generateType)) {
                // 为动态模板
                if (inputPath.endsWith(".ftl")) {
                    fileInfo.setGenerateType(FileGenerateTypeEnum.DYNAMIC.getValue());
                } else {
                    fileInfo.setGenerateType(FileGenerateTypeEnum.STATIC.getValue());

                }
            }

        }

    }

    // 基础信息校验和默认值
    private static void validAndFillMetaRoot(Meta meta) {
        // 校验并填充默认值
        String name = StrUtil.blankToDefault(meta.getName(), "my-generator");
        String description = StrUtil.emptyToDefault(meta.getDescription(), "我的模板代码生成器");
        String author = StrUtil.emptyToDefault(meta.getAuthor(), "shing");
        String basePackage = StrUtil.blankToDefault(meta.getBasePackage(), "com.shing");
        String version = StrUtil.emptyToDefault(meta.getVersion(), "1.0");
        String createTime = StrUtil.emptyToDefault(meta.getCreateTime(), DateUtil.now());
        meta.setName(name);
        meta.setDescription(description);
        meta.setAuthor(author);
        meta.setBasePackage(basePackage);
        meta.setVersion(version);
        meta.setCreateTime(createTime);
    }
}

