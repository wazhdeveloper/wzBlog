package com.wz.blogbackstage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wz.blogcommon.bean.Article;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wazh
 * @since 2023-10-22-16:49
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
    Long getMaxId();
}
