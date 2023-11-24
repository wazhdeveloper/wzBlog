package com.wz.blogforeground.service;

import com.wz.blogcommon.bean.Category;
import com.wz.blogcommon.bean.vo.CategoryVo;

import java.util.List;

/**
 * @author wazh
 * @since 2023-10-10-19:13
 */
public interface CategoryService {

    List<CategoryVo> getCategoryList();

    Category getByCategoryId(Long id);
}
