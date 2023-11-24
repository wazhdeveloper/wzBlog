package com.wz.blogforeground.job;

import com.wz.blogcommon.bean.Article;
import com.wz.blogcommon.utils.RedisCache;
import com.wz.blogforeground.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wazh
 * @since 2023-10-17-19:58
 */
@Slf4j
@Component
public class UpdateViewCountJob {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ArticleService articleService;

    //定时任务，定时更新数据库中的浏览量
    @Scheduled(cron = "0/50 * * * * ?")
    public void updateViewCount() {
        log.info("定时任务开启：更新数据库文章浏览量中~~");
        //获取redis中的浏览量
        Map<String, Integer> viewCountMap = redisCache.getCacheMap("article:viewCount");

        List<Article> articles = viewCountMap.entrySet()
                .stream()
                .map(entry -> new Article(Long.valueOf(entry.getKey()), entry.getValue().longValue()))
                .collect(Collectors.toList());
        //更新到数据库中
        articleService.updateBatchById(articles);
    }
}