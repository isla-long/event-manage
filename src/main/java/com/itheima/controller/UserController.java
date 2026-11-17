package com.itheima.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itheima.pojo.Result;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import com.itheima.utils.JwtUtil;
import com.itheima.utils.Md5Util;
import com.itheima.utils.ThreadLocalUtil;

import jakarta.validation.constraints.Pattern;

@Validated 
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired 
    private UserService userService;

    @PostMapping("/register")
    public Result<?> register(@Pattern(regexp = "^\\S{5,16}$",message = "用户名长度不合法") @RequestParam String username,@Pattern(regexp = "^\\S{5,16}$",message = "密码长度不合法") @RequestParam String password){
    userService.register(username, password);
    return Result.success();
}

    @PostMapping("/login")
    public Result<?> login(@Pattern(regexp = "^\\S{5,16}$",message = "用户名长度不合法") @RequestParam String username,@Pattern(regexp = "^\\S{5,16}$",message = "密码长度不合法") @RequestParam String password){
        User loginUser = userService.findByUserId(username);
        if(loginUser == null){
            return Result.error("用户不存在");
        }
        if(!Md5Util.getMD5String(password).equals(loginUser.getPassword())){
            return Result.error("密码错误");
        }
        Map<String, Object> claim = new HashMap<>();
        claim.put("id", loginUser.getId());
        claim.put("username", loginUser.getUsername());
        String token = JwtUtil.genToken(claim);
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(token,token,30,TimeUnit.DAYS);
        return Result.success(token);
    }

    @GetMapping("/userInfo")
    public Result<?> userInfo(){
        Map<String,Object> claim = ThreadLocalUtil.get();
        String username = (String) claim.get("username");
        User user = userService.findByUserId(username);
        return Result.success(user);
    }

    @PutMapping("/update")
    public Result<?> update(@RequestBody @Validated User user){
        userService.update(user);
        return Result.success();
    }
    
    @PatchMapping("/updateAvatar")
    public Result<?> updateAvatar(@RequestParam @URL String avatarUrl){
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    @PatchMapping("/updatePwd")
    public Result<?> updatePwd(@RequestBody Map<String,String> map,@RequestHeader("Authorization") String token){
        String old_pwd = map.get("old_pwd");
        String new_pwd = map.get("new_pwd");
        String re_pwd = map.get("re_pwd");
        if(old_pwd.isEmpty() || new_pwd.isEmpty() || re_pwd.isEmpty()){
            return Result.error("参数不能为空");
        }
        Map<String,Object> claim = ThreadLocalUtil.get();
        String username = (String) claim.get("username");
        if(!Md5Util.getMD5String(old_pwd).equals(userService.findByUserId(username).getPassword())){
            return Result.error("旧密码错误");
        }
        if(!new_pwd.equals(re_pwd)){
            return Result.error("两次密码不一致");
        }
        userService.updatePwd(new_pwd);
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.getOperations().delete(token);
        return Result.success();
    }
}
