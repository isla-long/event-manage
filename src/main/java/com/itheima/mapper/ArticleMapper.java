package com.itheima.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.itheima.pojo.Article;

@Mapper 
public interface ArticleMapper {
    
    @Insert("insert into article (title, content, cover_img, state, category_id, create_user, create_time, update_time) values (#{title}, #{content}, #{coverImg}, #{state}, #{categoryId}, #{createUser}, #{createTime}, #{updateTime})")
    void add(Article article);

    List<Article> list(Integer categoryId, String state, int userID);

    @Select("select * from article where id = #{id}")
    Article detail(Integer id);

    @Update("update article set title = #{title}, content = #{content}, cover_img = #{coverImg}, state = #{state}, category_id = #{categoryId}, update_time = #{updateTime} where id = #{id} and create_user = #{createUser}")
    void update(Article article);

    @Delete("delete from article where id = #{id} and create_user = #{userID}")
    void delete(Integer id, int userID);
}
