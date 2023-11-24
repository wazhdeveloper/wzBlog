package com.wz.blogforeground.mapper;

import com.wz.blogcommon.bean.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArticleMapper {

    List<Article> getHotArticles();

    List<Article> getArticleList(@Param("pageStart") Integer pageStart, @Param("offset") Integer offset, @Param("categoryId") Integer categoryId);

    Article getArticleById(@Param("id") Long id);

    List<Article> getAllArticle();

    void updateBatchById(Article article);
}
