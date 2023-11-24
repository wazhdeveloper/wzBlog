package com.wz.blogbackstage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wz.blogbackstage.mapper.ArticleMapper;
import com.wz.blogbackstage.service.ArticleService;
import com.wz.blogcommon.bean.Article;
import com.wz.blogcommon.bean.vo.PageVo;
import com.wz.blogcommon.result.ResponseResult;
import com.wz.blogcommon.utils.SystemConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author wazh
 * @since 2023-10-22-16:49
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public Long getMaxId() {
        return articleMapper.getMaxId();
    }

    @Override
    public ResponseResult<?> listArticles(Integer pageNum, Integer pageSize, String title, String summary) {
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StringUtils.hasText(title), Article::getTitle, title);
        lambdaQueryWrapper.like(StringUtils.hasText(summary), Article::getSummary, summary);
        lambdaQueryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);

        Page<Article> page = new Page<>(pageNum, pageSize);
        page(page, lambdaQueryWrapper);
        long total = page.getTotal();
        List<Article> records = page.getRecords();
        PageVo pageVo = new PageVo(records, total);
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult<?> getArticleById(Long id) {
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Article::getId, id);
        lambdaQueryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);

        Article article = getBaseMapper().selectOne(lambdaQueryWrapper);
        return ResponseResult.okResult(article);
    }
}
