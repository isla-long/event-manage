package com.itheima.pojo;

import java.time.LocalDateTime;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data // 使用lombok的@Data注解，自动生成getter和setter方法
@AllArgsConstructor // 使用lombok的@AllArgsConstructor注解，自动生成构造方法
@NoArgsConstructor // 使用lombok的@NoArgsConstructor注解，自动生成无参构造方法
@Validated // 使用@Validated注解，自动验证参数
public class User { 

    @NotNull(message = "id不能为空")
    private Integer id;
    private String username;
    @JsonIgnore // 使用@JsonIgnore注解，在json化的时候忽略

    private String password;
    @NotEmpty
    @Pattern(regexp = "^\\S{1,10}$",message = "昵称长度不合法") 
    private String nickname;
    @NotEmpty(message = "邮箱不能为空")
    @Email(message = "邮箱格式不合法")
    private String email;
    private String avatar;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    
}
