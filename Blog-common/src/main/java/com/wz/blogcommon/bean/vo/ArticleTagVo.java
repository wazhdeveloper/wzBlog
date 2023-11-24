package com.wz.blogcommon.bean.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author wazh
 * @since 2023-10-22-16:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleTagVo {

    private Long id;

    private String title;

    private String content;

    private String summary;
    //id
    private Long categoryId;

    private String thumbnail;
    //01
    private String isTop;
    //01
    private String status;
    // 10
    private String isComment;

    private List<Long> tags;
}
