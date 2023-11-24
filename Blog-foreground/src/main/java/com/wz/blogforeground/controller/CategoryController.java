package com.wz.blogforeground.controller;

import com.wz.blogcommon.annotation.SystemLog;
import com.wz.blogcommon.bean.vo.CategoryVo;
import com.wz.blogcommon.result.ResponseResult;
import com.wz.blogforeground.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wazh
 * @since 2023-10-10-19:14
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getCategoryList")
    @SystemLog(businessName = "获取分类列表")
    public ResponseResult<?> getCategoryList() {
        List<CategoryVo> vos = categoryService.getCategoryList();
        return ResponseResult.okResult(vos);
    }
}
