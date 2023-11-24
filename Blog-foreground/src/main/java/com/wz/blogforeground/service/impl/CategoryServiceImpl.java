package com.wz.blogforeground.service.impl;

import com.wz.blogcommon.bean.Category;
import com.wz.blogcommon.bean.vo.CategoryVo;
import com.wz.blogcommon.utils.BeanCopyUtil;
import com.wz.blogforeground.mapper.CategoryMapper;
import com.wz.blogforeground.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wazh
 * @since 2023-10-10-19:13
 */
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryVo> getCategoryList() {
        List<Category> categories = categoryMapper.getCategoryList();
        return BeanCopyUtil.copyBeanList(categories, CategoryVo.class);
    }

    @Override
    public Category getByCategoryId(Long id) {
        return categoryMapper.getById(id);
    }


}
