package com.wz.blogforeground.runner;

import com.wz.blogcommon.bean.Article;
import com.wz.blogcommon.utils.RedisCache;
import com.wz.blogforeground.mapper.ArticleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 用于项目启动时，预先加载数据
 * CommandLineRunner: 事件监听器
 */
@Slf4j
@Component
public class TestRunner implements CommandLineRunner {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private RedisCache redisCache;

    @Override
    public void run(String... args) throws Exception {
        //项目启动时，将文章浏览量存放进数据库map形式
        List<Article> articles = articleMapper.getAllArticle();
        Map<String, Integer> viewCountMap = articles.stream()
                .collect(Collectors.toMap(article -> article.getId().toString(), article -> article.getViewCount().intValue()));
        redisCache.setCacheMap("article:viewCount", viewCountMap);
    }
}
