package com.itheima.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itheima.pojo.Category;
import com.itheima.pojo.Result;
import com.itheima.service.CategoryService;

@RestController 
@RequestMapping("/category")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public Result<?> add(@RequestBody @Validated(Category.Add.class) Category category){
        categoryService.add(category);
        return Result.success();
    }

    @GetMapping 
    public Result<?> list(){
        List<Category> categories = categoryService.list();
        return Result.success(categories);
    }

    @GetMapping("/detail")
    public Result<?> detail(@RequestParam int id){
        Category category = categoryService.findById(id);
        return Result.success(category);
}
    @PutMapping
    public Result<?> update(@RequestBody @Validated(Category.Update.class) Category category){
        categoryService.update(category);
        return Result.success();
    }

    @DeleteMapping 
    public Result<?> delete(@RequestParam int id){
        categoryService.delete(id);
        return Result.success();
    }
}
