package com.shing.web.mapper;

import com.shing.web.model.entity.Generator;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author LBC
* @description 针对表【generator(代码生成器)】的数据库操作Mapper
* @createDate 2024-02-26 18:19:55
* @Entity com.shing.web.model.entity.Generator
*/
public interface GeneratorMapper extends BaseMapper<Generator> {
    @Select("SELECT id, distPath FROM generator WHERE isDelete = 1")

    List<Generator> listDeletedGenerator();
}




