package com.wz.blogbackstage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wz.blogcommon.bean.Category;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wazh
 * @since 2023-10-22-10:40
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}
