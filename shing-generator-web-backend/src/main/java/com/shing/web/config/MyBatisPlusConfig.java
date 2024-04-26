package com.shing.web.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.cong.sql.slowmirror.config.ConfigUtils;
import com.cong.sql.slowmirror.core.SqlAnalysisAspect;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * MyBatis Plus 配置
 *
 * @author https://github.com/lishing
 */
@Configuration
@MapperScan("com.shing.web.mapper")
public class MyBatisPlusConfig {

    /**
     * 拦截器配置
     *
     * @return
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 分页插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    /**
     * SQL分析方面
     *
     * @return {@link SqlAnalysisAspect}
     */
    @Bean
    public SqlAnalysisAspect sqlAnalysisAspect() {

        // 加载配置文件，此处加载的是名为"sql.slow.mirror"的配置
        Properties properties = ConfigUtils.loadConfig("sql.slow.mirror");

        // 创建SQL分析切面的实例
        SqlAnalysisAspect sqlAnalysisAspect = new SqlAnalysisAspect();

        // 将加载的配置属性设置到SQL分析切面实例中
        sqlAnalysisAspect.setProperties(properties);

        // 返回配置完毕的SQL分析切面实例
        return sqlAnalysisAspect;
    }
}