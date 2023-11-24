package com.wz.blogcommon.bean.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wazh
 * @since 2023-10-12-9:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleVo implements Serializable {
    private Long categoryId;
    private String categoryName;
    private String content;
    private Date createTime;
    private Long id;
    private String isComment;
    private String title;
    private Long viewCount;
}
