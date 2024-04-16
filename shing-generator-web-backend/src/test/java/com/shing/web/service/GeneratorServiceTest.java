package com.shing.web.service;

import com.shing.web.model.entity.Generator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author shing
 */
@SpringBootTest
class GeneratorServiceTest {

    @Resource
    private GeneratorService generatorService;

    @Test
    public void testInsert() {
        Generator generator = generatorService.getById(19L);
        for (int i = 0; i < 10000; i++) {
            generator.setId(null);
            generatorService.save(generator);
        }


    }

}