package com.wz.blogforeground.dao;

import com.wz.blogcommon.bean.Article;

import com.wz.blogforeground.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wazh
 * @since 2023-10-10-12:59
 */
@Component
public class ArticleDao {

    @Autowired
    private ArticleService articleService;

    public List<Article> getArticleList(Integer pageNum, Integer pageSize, Integer categoryId) {
        Integer pageStart = (pageNum - 1) * pageSize;
        return articleService.getArticleList(pageStart, pageSize, categoryId);
    }

}
