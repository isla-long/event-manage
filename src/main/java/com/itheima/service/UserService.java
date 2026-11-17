package com.itheima.service;

import com.itheima.pojo.User;

public interface UserService {

    User findByUserId(String username);

    void register(String username,String password);

    void update(User user);

    void updateAvatar(String avatarURL);

    void updatePwd(String  password);
}
