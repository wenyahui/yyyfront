package com.bootdo.web.dao;

import java.util.List;
import java.util.Map;

import com.bootdo.web.entity.Article;

public interface ArticleDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);
    
    void delArticles(String ids);
    List<Map<String, Object>> getArticleList(Map<String, Object> paramMap);
    int queryArticleCount(Map<String, Object> paramMap);
}