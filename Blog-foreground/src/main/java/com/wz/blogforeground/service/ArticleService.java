package com.wz.blogforeground.service;

import com.wz.blogcommon.bean.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author wazh
 * @since 2023-10-09-13:58
 */
public interface ArticleService {

    List<Article> getHotArticles();

    List<Article> getArticleList(Integer pageStart, Integer offset, Integer categoryId);

    Article getArticleById(Long id);

    void incrementArticleViewCount(String key);

    void updateBatchById(List<Article> articles);

    List<Article> listArticles(Integer pageNum, Integer pageSize, String title, String summary);
}
