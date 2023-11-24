package com.wz.blogforeground.controller;

import com.wz.blogcommon.annotation.SystemLog;
import com.wz.blogcommon.bean.Comment;
import com.wz.blogcommon.result.ResponseResult;
import com.wz.blogcommon.utils.SystemConstants;
import com.wz.blogforeground.dao.CommentDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wazh
 * @since 2023-10-14-16:38
 */
@Slf4j
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentDao commentDao;

    @GetMapping("/commentList")
    @SystemLog(businessName = "获取评论列表")
    public ResponseResult<?> getCommentList(
            @RequestParam("articleId") Long articleId,
            @RequestParam("pageNum") Integer pageNum,
            @RequestParam("pageSize") Integer pageSize) {
        return commentDao.getCommentList(SystemConstants.ARTICLE_COMMENT, articleId, pageNum, pageSize);
    }

    @PostMapping
    @SystemLog(businessName = "发表评论")
    public ResponseResult<?> comment(@RequestBody Comment comment) {
        commentDao.addComment(comment);
        return ResponseResult.okResult();
    }

    @GetMapping("/linkCommentList")
    @SystemLog(businessName = "获取友链评论列表")
    public ResponseResult<?> getLinkCommentList (
            @RequestParam("pageNum") Integer pageNum,
            @RequestParam("pageSize") Integer pageSize
    ) {
        return commentDao.getCommentList(SystemConstants.LINK_COMMENT, null, pageNum, pageSize);
    }
}
