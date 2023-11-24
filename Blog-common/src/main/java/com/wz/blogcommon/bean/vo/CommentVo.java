package com.wz.blogcommon.bean.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author wazh
 * @since 2023-10-14-17:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentVo {
    @TableId(type = IdType.AUTO)
    private Long id;
    //文章id
    private Long articleId;
    //根评论id
    private Long rootId;
    //评论内容
    private String content;
    //所回复的目标评论的userid
    private Long toCommentUserId;
    private String toCommentUserName;
    //回复目标评论id
    private Long toCommentId;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long createBy;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    private String username;
    //子评论
    private List<CommentVo> children;
}
