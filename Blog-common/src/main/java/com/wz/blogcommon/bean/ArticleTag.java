package com.wz.blogcommon.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wazh
 * @since 2023-10-22-17:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("wz_article_tag")
public class ArticleTag {

    private Long articleId;

    private Long tagId;
}
