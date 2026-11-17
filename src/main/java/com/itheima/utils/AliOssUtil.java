package com.itheima.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.aliyun.sdk.service.oss2.OSSClient;
import com.aliyun.sdk.service.oss2.credentials.CredentialsProvider;
import com.aliyun.sdk.service.oss2.credentials.EnvironmentVariableCredentialsProvider;
import com.aliyun.sdk.service.oss2.models.PutObjectRequest;
import com.aliyun.sdk.service.oss2.models.PutObjectResult;
import com.aliyun.sdk.service.oss2.transport.BinaryData;

/**
 * OSS 上传工具。
 * 由 Spring 管理：endpoint / region / bucket 从 application.yaml 的 aliyun.oss.* 注入。
 */
@Component
public class AliOssUtil {

    private final String endpoint;
    private final String region;
    private final String bucket;

    public AliOssUtil(@Value("${aliyun.oss.endpoint}") String endpoint,
                      @Value("${aliyun.oss.region}") String region,
                      @Value("${aliyun.oss.bucket}") String bucket) {
        this.endpoint = endpoint;
        this.region = region;
        this.bucket = bucket;
    }



    public String upload(InputStream inputStream, String objectName) {
        CredentialsProvider provider = new EnvironmentVariableCredentialsProvider();

        try (OSSClient client = OSSClient.newBuilder()
                .credentialsProvider(provider)
                .region(region)
                .endpoint(endpoint)
                .build()) {

            PutObjectResult result = client.putObject(PutObjectRequest.newBuilder()
                    .bucket(bucket)
                    .key(objectName)
                    .body(BinaryData.fromStream(inputStream))
                    .build());

            System.out.printf("status code:%d, request id:%s, eTag:%s%n",
                    result.statusCode(), result.requestId(), result.eTag());

            // endpoint = https://oss-cn-shenzhen.aliyuncs.com
            //         -> https://hosh1no.oss-cn-shenzhen.aliyuncs.com/ba/pic.jpg
            String[] parts = endpoint.split("//");
            return parts[0] + "//" + bucket + "." + parts[1] + "/" + objectName;

        } catch (Exception e) {
            // 不要吞异常，否则调用方拿到 null 也不知道为什么失败
            throw new IllegalStateException("上传 OSS 失败: " + objectName, e);
        }
    }
}
