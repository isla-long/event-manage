package com.itheima;

import java.io.FileInputStream;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itheima.utils.AliOssUtil;

/**
 * 必须 @SpringBootTest：AliOssUtil 是 Spring Bean，
 * 配置项要靠容器从 application.yaml 注入，直接 new 出来字段全是 null。
 */
@SpringBootTest
public class Aliyuntest {

    @Autowired
    private AliOssUtil aliOssUtil;

    @Test
    public void testPutObject() throws Exception {
        FileInputStream inputStream = new FileInputStream("/Users/hoshino/Pictures/ba/87977950_p0.jpg");
        String url = aliOssUtil.upload(
                inputStream,
                "ba/87977950_p0.jpg");
        System.out.println("上传成功: " + url);
    }
}
