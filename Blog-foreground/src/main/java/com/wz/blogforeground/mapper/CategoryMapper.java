package com.wz.blogforeground.mapper;

import com.wz.blogcommon.bean.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author wazh
 * @since 2023-10-10-19:12
 */
@Mapper
public interface CategoryMapper {

    List<Category> getCategoryList();

    Category getById(Long id);
}
