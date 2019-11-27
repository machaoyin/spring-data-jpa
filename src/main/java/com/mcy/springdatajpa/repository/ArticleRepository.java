package com.mcy.springdatajpa.repository;

import com.mcy.springdatajpa.entity.Article;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ArticleRepository extends PagingAndSortingRepository<Article, Integer> {

}
