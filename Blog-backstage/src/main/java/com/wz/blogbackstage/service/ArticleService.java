package com.wz.blogbackstage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wz.blogcommon.bean.Article;
import com.wz.blogcommon.result.ResponseResult;

/**
 * @author wazh
 * @since 2023-10-22-16:49
 */
public interface ArticleService extends IService<Article> {

    Long getMaxId();

    ResponseResult<?> listArticles(Integer pageNum, Integer pageSize, String title, String summary);

    ResponseResult<?> getArticleById(Long id);
}
