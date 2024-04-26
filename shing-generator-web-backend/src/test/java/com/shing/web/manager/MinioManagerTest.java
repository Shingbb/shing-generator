package com.shing.web.manager;

import io.minio.BucketExistsArgs;
import io.minio.MinioClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author shing
 */
@SpringBootTest
class MinioManagerTest {

    @Resource
    private MinioManager minioManager;

    @Resource
    private MinioClient minioClient;

    @Test
    void testMinio() throws Exception {
        minioManager.testMinio();
    }

    @Test
    void testBucketExists() throws Exception {
        boolean isBucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket("generator").build());
        System.out.println("目录是否存在" + isBucketExists);
    }
}