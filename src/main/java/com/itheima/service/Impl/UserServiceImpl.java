package com.itheima.service.Impl;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itheima.mapper.UserMapper;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import com.itheima.utils.Md5Util;
import com.itheima.utils.ThreadLocalUtil;
@Service 
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUserId(String username) {
        return userMapper.findByUserId(username);
    }

    @Override
    public void register(String username,String password) {
        // 把明文密码转成 MD5 后再入库
        String md5Pwd = Md5Util.getMD5String(password);
        if(userMapper.findByUserId(username) != null){
            throw new RuntimeException("用户名已存在");
        }
        userMapper.register(username, md5Pwd);
    }

    @Override
    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    @Override
    public void updateAvatar(String avatarURL) {
        Map<String,Object> user = ThreadLocalUtil.get();
        int id = (int) user.get("id");
        userMapper.updateAvatar(avatarURL,id);
    }

    @Override
    public void updatePwd(String password) {
        password = Md5Util.getMD5String(password);
        Map<String,Object> user = ThreadLocalUtil.get();
        int id = (int) user.get("id");
        userMapper.updatePwd(password,id);
    }
}
