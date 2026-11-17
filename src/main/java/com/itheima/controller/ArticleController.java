package com.itheima.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itheima.pojo.Article;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Result;
import com.itheima.service.ArticleService;
import com.itheima.utils.JwtUtil;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired 
    private ArticleService articleService;

    @PostMapping 
    public Result<?> add(@RequestBody @Validated(Article.Add.class) Article article){
        articleService.add(article);
        return Result.success();
    }
    @GetMapping
    public Result<PageBean<Article>> list(Integer pageNum,
        Integer pageSize,
        @RequestParam(required = false) Integer categoryId,
        @RequestParam(required = false) String state){
        return Result.success(articleService.list(pageNum, pageSize, categoryId, state));
    }
    @GetMapping("/detail")
    public Result<Article> detail(@RequestParam @NotNull Integer id){
        return Result.success(articleService.detail(id));
    }

    @PutMapping
    public Result<?> update(@RequestBody @Validated(Article.Update.class) Article article){
        articleService.update(article);
        return Result.success();
    }

    @DeleteMapping 
    public Result<?> delete(@RequestParam @NotNull Integer id){
        articleService.delete(id);
        return Result.success();
    }
}