package com.wz.blogforeground.service.impl;

import com.wz.blogcommon.bean.Comment;
import com.wz.blogcommon.bean.vo.CommentVo;
import com.wz.blogcommon.bean.vo.PageVo;
import com.wz.blogcommon.result.ResponseResult;
import com.wz.blogcommon.utils.BeanCopyUtil;
import com.wz.blogcommon.utils.SystemConstants;
import com.wz.blogforeground.mapper.CommentMapper;
import com.wz.blogforeground.service.CommentService;
import com.wz.blogforeground.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;

/**
 * @author wazh
 * @since 2023-10-14-16:39
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private UserService userService;
    @Override
    public ResponseResult<?> getCommentList(String commentType, Long articleId, Integer pageStart, Integer pageSize) {
        List<Comment> commentList = commentMapper.getCommentList(commentType, articleId, pageStart, pageSize);
        List<CommentVo> commentVos = copyToCommentVo(commentList);
        for (CommentVo commentVo : commentVos) {
            List<Comment> commentById = commentMapper.getCommentById(commentVo.getId());
            List<CommentVo> commentVos1 = copyToCommentVo(commentById);
            commentVo.setChildren(commentVos1);
        }
        return ResponseResult.okResult(new PageVo(commentVos, getTotal(commentVos, SystemConstants.START_OF_TOTAL)));
    }

    @Override
    public ResponseResult<?> addComment(Comment comment) {
        Integer integer = commentMapper.addComment(comment);
        return ResponseResult.okResult(integer);
    }

    public List<CommentVo> copyToCommentVo(List<Comment> commentList) {
        List<CommentVo> commentVos = BeanCopyUtil.copyBeanList(commentList, CommentVo.class);
        //遍历vo集合
        for (CommentVo commentVo : commentVos) {
            //通过createBy查询用户的昵称并赋值
            String nickName = userService.getUserById(commentVo.getCreateBy()).getNickName();
            commentVo.setUsername(nickName);
            //通过toCommentUserId查询用户的昵称并赋值
            //如果toCommentUserId不为-1才进行查询
            if (commentVo.getToCommentUserId() != -1) {
                String toCommentUserName = userService.getUserById(commentVo.getToCommentUserId()).getNickName();
                commentVo.setToCommentUserName(toCommentUserName);
            }
        }
        return commentVos;
    }

    //递归查询所有评论数量
    public Long getTotal(List<CommentVo> commentVoList, Long total) {
        if (commentVoList != null) {
            total += commentVoList.size();
            for (CommentVo commentVo : commentVoList) {
                if (commentVo.getChildren() != null) {
                    total = getTotal(commentVo.getChildren(), total);
                }
            }
        }
        return total;
    }
}
