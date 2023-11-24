package com.wz.blogforeground.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wz.blogcommon.bean.Article;
import com.wz.blogcommon.utils.RedisCache;
import com.wz.blogforeground.mapper.ArticleMapper;
import com.wz.blogforeground.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wazh
 * @since 2023-10-09-13:58
 */
@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private RedisCache redisCache;

    @Override
    public List<Article> getHotArticles() {
        return articleMapper.getHotArticles();
    }

    @Override
    public List<Article> getArticleList(Integer pageStart, Integer offset, Integer categoryId) {
        return articleMapper.getArticleList(pageStart, offset, categoryId);
    }

    @Override
    public Article getArticleById(Long id) {
        return articleMapper.getArticleById(id);
    }

    @Override
    public void incrementArticleViewCount(String key) {
        redisCache.incrementCacheMapValue("article:viewCount", key, 1);
    }

    /**
     * 批量更新文章浏览量
     * @param articles
     */
    @Override
    public void updateBatchById(List<Article> articles) {
        for (Article article : articles) {
            articleMapper.updateBatchById(article);
        }
    }

    @Override
    public List<Article> listArticles(Integer pageNum, Integer pageSize, String title, String summary) {
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Article::getTitle, title);
        lambdaQueryWrapper.eq(Article::getSummary, summary);

        Page<Article> page = new Page<>(pageNum, pageSize);


        return null;
    }
}
