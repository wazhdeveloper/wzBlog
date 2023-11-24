package com.wz.blogforeground.dao;

import com.wz.blogcommon.bean.Comment;
import com.wz.blogcommon.result.ResponseResult;
import com.wz.blogcommon.utils.DateUtil;
import com.wz.blogcommon.utils.SecurityUtils;
import com.wz.blogforeground.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;

/**
 * @author wazh
 * @since 2023-10-14-16:53
 */
@Component
public class CommentDao {

    @Autowired
    private CommentService commentService;

    public ResponseResult<?> getCommentList(String commentType, Long articleId, Integer pageNum, Integer pageSize) {
        return commentService.getCommentList(commentType, articleId, (pageNum - 1) * pageSize, pageSize);
    }

    public ResponseResult<?> addComment(Comment comment) {
        Date now = DateUtil.getNow();
        comment.setCreateTime(now);
        Long userId = SecurityUtils.getUserId(); //通过token从securityContentHolder中获取存储的loginUser的userId
        comment.setCreateBy(userId);//创建评论人的id
        comment.setUpdateBy(userId);//更新人的id
        comment.setUpdateTime(now); //更新时间
        comment.setDelFlag(0); // 逻辑删除值初始化为0
        return commentService.addComment(comment);
    }
}
