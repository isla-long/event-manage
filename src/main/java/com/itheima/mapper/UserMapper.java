package com.itheima.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.itheima.pojo.User;

@Mapper 
public interface UserMapper {
    

    @Select("select * from user where username = #{username}")
    User findByUserId(String username);

    @Insert("insert into user (username,password,create_time,update_time) values (#{username},#{password},now(),now())")
    void register(String username,String password);

    @Update("update user set username = #{username},nickname = #{nickname},email = #{email},update_time = #{updateTime} where id = #{id}")
    void update(User user);

    @Update("update user set avatar = #{avatarURL},update_time = now() where id = #{id}")
    void updateAvatar(String avatarURL,int id);

    @Update("update user set password = #{password},update_time = now() where id = #{id}")
    void updatePwd(String password,int id);
}
