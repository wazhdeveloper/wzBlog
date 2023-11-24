package com.wz.blogforeground.controller;

import com.wz.blogcommon.annotation.SystemLog;
import com.wz.blogcommon.bean.Article;
import com.wz.blogcommon.bean.vo.ArticleListVo;
import com.wz.blogcommon.bean.vo.ArticleVo;
import com.wz.blogcommon.bean.vo.HotArticleVo;
import com.wz.blogcommon.bean.vo.PageVo;
import com.wz.blogcommon.utils.RedisCache;
import com.wz.blogforeground.dao.ArticleDao;
import com.wz.blogcommon.result.ResponseResult;
import com.wz.blogforeground.service.ArticleService;
import com.wz.blogcommon.utils.BeanCopyUtil;
import com.wz.blogforeground.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController("article")
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RedisCache redisCache;

    /**
     * @return 返回前十的热门文章
     */
    @GetMapping("/getHotArticles")
    @SystemLog(businessName = "获取热门文章")
    public ResponseResult<?> getHotArticles() {
        List<Article> articles = articleService.getHotArticles();
        List<HotArticleVo> hotArticles = BeanCopyUtil.copyBeanList(articles, HotArticleVo.class);
        return ResponseResult.okResult(hotArticles);
    }

    @GetMapping("/articleList")
    @SystemLog(businessName = "获取文章列表")
    public ResponseResult<?> getArticleList(
            @RequestParam("pageNum") Integer pageNum,
            @RequestParam("pageSize") Integer pageSize,
            @RequestParam("categoryId") Integer categoryId
    ) {
        List<Article> articles = articleDao.getArticleList(pageNum, pageSize, categoryId);
        List<Article> articleList = articles.stream()
                .map(article -> article.setCategoryName(categoryService.getByCategoryId(article.getCategoryId()).getName()))
                .collect(Collectors.toList());
        List<ArticleListVo> articleListVos = BeanCopyUtil.copyBeanList(articleList, ArticleListVo.class);
        PageVo pageVo = new PageVo(articleListVos, (long) articleListVos.size());
        return ResponseResult.okResult(pageVo);
    }

    @GetMapping("/{id}")
    @SystemLog(businessName = "根据文章id获取文章")
    public ResponseResult<?> getArticleById(@PathVariable("id") Long id) {
        Article article = articleService.getArticleById(id);
        ArticleVo articleVo = BeanCopyUtil.copyBean(article, ArticleVo.class);
        articleVo.setCategoryName(categoryService.getByCategoryId(article.getCategoryId()).getName());
        // 从redis中获取浏览量
        String vc = redisCache.getCacheMapValue("article:viewCount", id.toString());
        articleVo.setViewCount(Long.parseLong(vc));
        return ResponseResult.okResult(articleVo);
    }

    @PutMapping("/updateViewCount/{id}")
    @SystemLog(businessName = "更新文章浏览量")
    public ResponseResult<?> updateArticleViewCount(@PathVariable("id") Long id) {
        articleService.incrementArticleViewCount(id.toString());
        return ResponseResult.okResult();
    }
}
