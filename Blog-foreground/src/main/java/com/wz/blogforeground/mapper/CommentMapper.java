package com.wz.blogforeground.mapper;

import com.wz.blogcommon.bean.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wazh
 * @since 2023-10-14-16:40
 */
@Mapper
public interface CommentMapper {
    List<Comment> getCommentList(@Param("commentType") String commentType, @Param("articleId") Long articleId, @Param("pageStart") Integer pageStart,@Param("pageSize") Integer pageSize);

    List<Comment> getCommentById(Long id);

    Integer addComment(Comment comment);
}
