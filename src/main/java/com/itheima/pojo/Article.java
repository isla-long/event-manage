package com.itheima.pojo;

import java.time.LocalDateTime;

import org.hibernate.validator.constraints.URL;

import com.itheima.anno.State;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.groups.Default;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data // 使用lombok的@Data注解，自动生成getter和setter方法
@AllArgsConstructor // 使用lombok的@AllArgsConstructor注解，自动生成构造方法
@NoArgsConstructor // 使用lombok的@NoArgsConstructor注解，自动生成无参构造方法
public class Article {
    @NotNull(message = "id不能为空",groups = Update.class)
    private Integer id;
    @NotEmpty(message = "标题不能为空")
    @Pattern(regexp = "^\\S{1,10}$",message = "标题格式不正确")
    private String title;
    @NotEmpty(message = "内容不能为空")
    private String content;
    @URL 
    private String coverImg;
    @NotEmpty(message = "状态不能为空")
    @State
    private String state;
    @NotNull(message = "分类id不能为空",groups = Update.class)
    private Integer categoryId;
    private Integer createUser;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public interface Add extends Default {    
    }
    public interface Update extends Default {
    }
}
