package com.wz.blogcommon.bean;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import com.wz.blogcommon.utils.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
@TableName("wz_article")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article implements Serializable {

    public Article(Long id, long viewCount) {
        this.id = id;
        this.viewCount = viewCount;
    }

    private static final long serialVersionUID = -6842131470754667710L;
    @TableId
    private Long id;

    private String title;

    private String content;

    private String summary;
    //id
    private Long categoryId;

    @TableField(exist = false)
    private String categoryName;

    private String thumbnail;
    //01
    private String isTop;
    //01
    private String status;

    private Long viewCount;
    // 10
    private String isComment;

    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime = DateUtil.getNow();

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime = DateUtil.getNow();
    //01
    @TableLogic(value = "0", delval = "1")
    private Integer delFlag = 0;

    public Article setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }
}

