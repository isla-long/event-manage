package com.itheima.service;

import java.util.List;

import com.itheima.pojo.Category;

public interface CategoryService {

    void add(Category category);

    List<Category> list();

    Category findById(int id);

    void update(Category category);

    void delete(int id);
}
