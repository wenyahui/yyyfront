package com.bootdo.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.bootdo.web.entity.Article;

@Transactional
public interface ArticleService {
	
	List<Map<String, Object>> getArticleList(Map<String, Object> paramMap);
	int queryArticleCount(Map<String, Object> paramMap);
	
	void addArticle(Article article);
	
	void delArticle(Integer id);
	void delArticles(String ids);
	
	Article queryArticleById(Integer id);
	
	void updateArticle(Article article);
	
}
