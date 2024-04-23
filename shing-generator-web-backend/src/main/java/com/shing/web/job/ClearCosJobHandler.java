package com.shing.web.job;

import cn.hutool.core.util.StrUtil;
import com.shing.web.manager.CosManager;
import com.shing.web.mapper.GeneratorMapper;
import com.shing.web.model.entity.Generator;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author shing
 */
@Component
@Slf4j
public class ClearCosJobHandler {

    @Resource
    private CosManager cosManager;

    @Resource
    private GeneratorMapper generatorMapper;

    /**
     * 每天执行
     *
     * @throws InterruptedException
     */
    @XxlJob("clearCosJobHandler")
    public void clearCosJobHandler() throws Exception {
        log.info("clearCosJobHandler start");
        // 编写业务逻辑
        // 1. 包括用户上传的模板制作文件（generator_make_template）
        cosManager.deleteDir("/generator_make_template/");

        // 2. 已删除的代码生成器对应的产物包文件（generator_dist）。
        List<Generator> generatorList = generatorMapper.listDeletedGenerator();
        List<String> keyList = generatorList.stream().map(Generator::getDistPath)
                .filter(StrUtil::isNotBlank)
                // 移除 '/' 前缀
                // substring(1) 是Java中字符串的一个方法，它返回从指定索引（包含）开始到字符串末尾的所有字符。在这里，索引值为1，意味着从第二个字符开始截取，相当于移除了字符串的第一个字符（也就是第一个字节或Unicode码位）。
                .map(distPath -> distPath.substring(1))
                .collect(Collectors.toList());

        cosManager.deleteObjects(keyList);
        log.info("clearCosJobHandler end");
    }

}