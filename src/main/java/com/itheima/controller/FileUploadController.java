package com.itheima.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.itheima.pojo.Result;
import com.itheima.utils.AliOssUtil;

import jakarta.validation.constraints.NotNull;

@RestController
public class FileUploadController {


    @Autowired
    private AliOssUtil aliOssUtil;

    @Value("${file.basePath}")
    private String basePath;

    @PostMapping("/upload")
    public Result<?> upload(@RequestParam @NotNull MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        if(originalFilename == null || originalFilename.isEmpty()) {
            return Result.error("文件名不能为空");
        }
        String objectName = originalFilename.substring(0,originalFilename.lastIndexOf(".")) + UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
        String url = aliOssUtil.upload(file.getInputStream(),"file/" + objectName);
        return Result.success(url);
    }
}
