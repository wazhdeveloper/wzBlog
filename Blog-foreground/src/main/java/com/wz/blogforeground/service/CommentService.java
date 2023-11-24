package com.wz.blogforeground.service;

import com.wz.blogcommon.bean.Comment;
import com.wz.blogcommon.result.ResponseResult;

import java.util.List;

/**
 * @author wazh
 * @since 2023-10-14-16:39
 */
public interface CommentService {

    ResponseResult<?> getCommentList(String comentType, Long articleId, Integer pageStart, Integer pageSize);

    ResponseResult<?> addComment(Comment comment);
}
