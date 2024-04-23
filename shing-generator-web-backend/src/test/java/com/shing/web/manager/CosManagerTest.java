package com.shing.web.manager;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @author shing
 */
@SpringBootTest
class CosManagerTest {

    @Resource
    CosManager cosManager;

    @Test
    void deleteObject() {
        cosManager.deleteObject("/test/test.png");
    }

    @Test
    void deleteObjects() {
        // 传入要删除的文件名
        // 注意文件名不允许以正斜线/或者反斜线\开头，例如：
        // 存储桶目录下有a/b/c.txt文件，如果要删除，只能是 keyList.add(new KeyVersion("a/b/c.txt")), 若使用 keyList.add(new KeyVersion("/a/b/c.txt"))会导致删除不成功
        cosManager.deleteObjects(Arrays.asList("test/test`.png", "test/test1.png"));
    }

    @Test
    void deleteDir() {
        cosManager.deleteDir("/test/");
    }
}