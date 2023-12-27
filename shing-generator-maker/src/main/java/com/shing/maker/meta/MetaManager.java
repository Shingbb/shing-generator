package com.shing.maker.meta;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.json.JSONUtil;

/**
 * @author LBC
 */
public class MetaManager {


    // volatile关键字是一种轻量级的同步机制，它主要用于保证多线程环境下的变量可见性
   private static volatile Meta meta;

    private MetaManager() {
        // 私有构造函数，防止外部实例化
    }

    // 获取Meta对象
    public static Meta getMetaObject() {
        // 双检锁
        if (meta == null) {
            synchronized (MetaManager.class) {
                if (meta == null) {
                    meta = initMeta();
                }
            }
        }
        return meta;
    }

    private static Meta initMeta() {
        String metaJson = ResourceUtil.readUtf8Str("meta.json");
        Meta newMeta = JSONUtil.toBean(metaJson, Meta.class);
        // 校验配置文件，处理默认值
        MetaValidator.doaValidatorAndFill(newMeta);
        return newMeta;

    }
}