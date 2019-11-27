package com.mcy.springdatajpa.service;

import com.mcy.springdatajpa.entity.Article;
import com.mcy.springdatajpa.repository.ArticleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ArticleService {
    //数据访问层接口对象
    @Resource
    private ArticleRepository articleRepository;

    //排序查询所有
    public Iterable<Article> findAllSort(Sort sort){
        return articleRepository.findAll(sort);
    }

    //分页查询所有
    public Page<Article> findAll(Pageable page){
        return articleRepository.findAll(page);
    }
}
