package com.itheima.service.Impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.ArticleMapper;
import com.itheima.pojo.Article;
import com.itheima.pojo.PageBean;
import com.itheima.service.ArticleService;
import com.itheima.utils.ThreadLocalUtil;

@Service 
public class ArticleServiceImpl implements ArticleService{

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void add(Article article) {
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());
        Map<String,Object> map = ThreadLocalUtil.get();
        article.setCreateUser((Integer)map.get("id"));
        articleMapper.add(article);
    }
    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        Map<String,Object> map = ThreadLocalUtil.get();
        int userID  = (int)map.get("id");
        PageHelper.startPage(pageNum,pageSize);
        List<Article> articles = articleMapper.list(categoryId,state,userID);
        Page<Article> page = (Page<Article>)articles;
        PageBean<Article> pageBean = new  PageBean<Article>(page.getTotal(),page.getResult());
        return pageBean;
    }
    @Override
    public Article detail(Integer id) {
        return articleMapper.detail(id);
    }
    @Override
    public void update(Article article) {
        article.setUpdateTime(LocalDateTime.now());
        Map<String,Object> map = ThreadLocalUtil.get();
        // createUser 放进 article 里，让 Mapper 只接收一个对象参数
        article.setCreateUser((Integer) map.get("id"));
        articleMapper.update(article);
    }
    @Override
    public void delete(Integer id) {
        Map<String,Object> map = ThreadLocalUtil.get();
        int userID = (int) map.get("id");
        articleMapper.delete(id,userID);
    }
}
