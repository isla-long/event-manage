package com.itheima.service.Impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itheima.mapper.CategoryMapper;
import com.itheima.pojo.Category;
import com.itheima.service.CategoryService;
import com.itheima.utils.ThreadLocalUtil;


@Service  
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public void add(Category category) {
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        Map<String,Object> user = ThreadLocalUtil.get();
        int id = (int) user.get("id");
        category.setCreateUser(id);
        categoryMapper.add(category);
    }

    @Override
    public List<Category> list() {
        Map<String,Object> user = ThreadLocalUtil.get();
        int id = (int) user.get("id");
        return categoryMapper.list(id);
    }

    @Override
    public Category findById(int id) {
        return categoryMapper.findById(id);
    }   

    @Override
    public void update(Category category) {
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.update(category);
    }

    @Override
    public void delete(int id) {
        categoryMapper.delete(id);
    }
}
