package com.wz.blogbackstage.controller;

import com.wz.blogbackstage.service.ArticleService;
import com.wz.blogbackstage.service.ArticleTagService;
import com.wz.blogcommon.annotation.SystemLog;
import com.wz.blogcommon.bean.Article;
import com.wz.blogcommon.bean.ArticleTag;
import com.wz.blogcommon.bean.vo.ArticleTagVo;
import com.wz.blogcommon.result.AppHttpCodeEnum;
import com.wz.blogcommon.result.ResponseResult;
import com.wz.blogcommon.utils.BeanCopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import reactor.util.annotation.Nullable;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wazh
 * @since 2023-10-22-16:31
 */
@RestController
@RequestMapping("/content/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleTagService articleTagService;

    @PostMapping
    @SystemLog(businessName = "上传博文")
    @Transactional
    public ResponseResult<?> postArticle(@RequestBody ArticleTagVo articleTagVo) {
        Article article = BeanCopyUtil.copyBean(articleTagVo, Article.class);
        articleService.save(article);
        Long id = articleService.getMaxId();
        List<ArticleTag> articleTags = articleTagVo.getTags().stream()
                .map(tagId -> new ArticleTag(id, tagId))
                .collect(Collectors.toList());
        articleTagService.saveBatch(articleTags);
        return ResponseResult.okResult();
    }

    @GetMapping("/list")
    @SystemLog(businessName = "分页查找指定文章")
    public ResponseResult<?> listArticles(
            @RequestParam("pageNum") Integer pageNum,
            @RequestParam("pageSize") Integer pageSize,
            @Nullable @RequestParam("title") String title,
            @Nullable @RequestParam("summary") String summary
    ) {
        return articleService.listArticles(pageNum, pageSize, title, summary);
    }

    @GetMapping("/{id}")
    @SystemLog(businessName = "通过id获取文章详细信息")
    public ResponseResult<?> getArticleById(@PathVariable("id") Long id) {
        return articleService.getArticleById(id);
    }

    @PutMapping
    @SystemLog(businessName = "更新文章")
    public ResponseResult<?> updateArticle(@RequestBody Article article) {
        boolean updated = articleService.updateById(article);
        if (!updated) return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR, "更新数据库失败，未找到id对应的文章");
        return ResponseResult.okResult();
    }

    @DeleteMapping("/{id}")
    @SystemLog(businessName = "删除文章")
    public ResponseResult<?> deleteArticleById(@PathVariable("id") Long id) {
        articleService.removeById(id);
        return ResponseResult.okResult();
    }
}
