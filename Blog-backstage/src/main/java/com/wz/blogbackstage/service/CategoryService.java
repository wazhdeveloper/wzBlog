package com.wz.blogbackstage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wz.blogcommon.bean.Category;
import com.wz.blogcommon.bean.vo.CategoryVo;
import com.wz.blogcommon.bean.vo.PageVo;

import java.util.List;

/**
 * @author wazh
 * @since 2023-10-22-10:37
 */
public interface CategoryService extends IService<Category> {
    List<Category> listAllCategory();

    PageVo listCategory(Integer pageNum, Integer pageSize, String name, String status);
}
