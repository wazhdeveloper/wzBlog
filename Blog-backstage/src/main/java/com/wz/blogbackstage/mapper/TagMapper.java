package com.wz.blogbackstage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wz.blogcommon.bean.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author wazh
 * @since 2023-10-18-15:08
 */
@Mapper
public interface TagMapper extends BaseMapper<Tag> {

    void setTagOfArticle(@Param("tagId") Long tagId, @Param("articleId") Long articleId);
}
