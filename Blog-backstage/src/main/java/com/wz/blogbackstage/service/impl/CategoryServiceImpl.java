package com.wz.blogbackstage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wz.blogbackstage.mapper.CategoryMapper;
import com.wz.blogbackstage.service.CategoryService;
import com.wz.blogcommon.bean.Category;
import com.wz.blogcommon.bean.vo.PageVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author wazh
 * @since 2023-10-22-10:38
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Override
    public List<Category> listAllCategory() {
        CategoryMapper categoryMapper = getBaseMapper();
        return categoryMapper.selectList(null);
    }

    @Override
    public PageVo listCategory(Integer pageNum, Integer pageSize, String name, String status) {
        LambdaQueryWrapper<Category> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StringUtils.hasText(name), Category::getName, name);
        lambdaQueryWrapper.eq(StringUtils.hasText(status), Category::getStatus, status);

        Page<Category> page = new Page<>(pageNum, pageSize);
        page(page, lambdaQueryWrapper);
        List<Category> categories = page.getRecords();
        long total = page.getTotal();
        return new PageVo(categories, total);
    }

}
