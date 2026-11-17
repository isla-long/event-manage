package com.itheima.pojo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data // 使用lombok的@Data注解，自动生成getter和setter方法
@AllArgsConstructor // 使用lombok的@AllArgsConstructor注解，自动生成构造方法
@NoArgsConstructor // 使用lombok的@NoArgsConstructor注解，自动生成无参构造方法
public class Category {
    @NotNull(message = "分类ID不能为空",groups = Update.class)
    private Integer id;
    @NotEmpty(message = "分类名称不能为空")
    private String categoryName;
    @NotEmpty(message = "分类别名不能为空")
    private String categoryAlias;
    private Integer createUser;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    //未指定则属于Default
    //组可以继承
    public interface Add extends Default{
    }
    public interface Update extends Default{
    }
    
}
