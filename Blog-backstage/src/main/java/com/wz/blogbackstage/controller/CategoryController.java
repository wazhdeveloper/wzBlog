package com.wz.blogbackstage.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.wz.blogbackstage.service.CategoryService;
import com.wz.blogcommon.annotation.SystemLog;
import com.wz.blogcommon.bean.Category;
import com.wz.blogcommon.bean.vo.CategoryVo;
import com.wz.blogcommon.bean.vo.PageVo;
import com.wz.blogcommon.result.AppHttpCodeEnum;
import com.wz.blogcommon.result.ResponseResult;
import com.wz.blogcommon.utils.BeanCopyUtil;
import com.wz.blogcommon.utils.WebUtils;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.util.annotation.Nullable;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author wazh
 * @since 2023-10-22-10:35
 */
@RestController
@RequestMapping("/content/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/listAllCategory")
    @SystemLog(businessName = "获取所有分类信息")
    public ResponseResult<?> listAllCategory() {
        List<Category> categories = categoryService.listAllCategory();
        List<CategoryVo> categoryVos = BeanCopyUtil.copyBeanList(categories, CategoryVo.class);
        return ResponseResult.okResult(categoryVos);
    }

    @GetMapping("/export")
    @PreAuthorize("@ps.hasPermission('content:category:export')")
    public void export(HttpServletResponse response) {
        try {
            List<Category> categories = categoryService.listAllCategory();
            WebUtils.setDownLoadHeader("分类.xlsx", response);
            ServletOutputStream outputStream = response.getOutputStream();
            EasyExcel.write(outputStream, Category.class).autoCloseStream(Boolean.FALSE).sheet("分类导出").doWrite(categories);
        } catch (IOException e) {
            //如果出现异常也要响应json
            ResponseResult<?> result = ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
            WebUtils.renderString(response, JSON.toJSONString(result));
        }
    }

    @GetMapping("/list")
    public ResponseResult<?> listCategories(
            @RequestParam("pageNum") Integer pageNum,
            @RequestParam("pageSize") Integer pageSize,
            @Nullable @RequestParam("name") String name,
            @Nullable @RequestParam("status") String status
    ) {
        PageVo pageVo = categoryService.listCategory(pageNum, pageSize, name, status);
        return ResponseResult.okResult(pageVo);
    }

    @PostMapping
    public ResponseResult<?> postCategory(@RequestBody Category category) {
        categoryService.save(category);
        return ResponseResult.okResult();
    }

    @GetMapping("/{id}")
    public ResponseResult<?> modifyCategory(@PathVariable("id") Long id) {
        Category category = categoryService.getById(id);
        return ResponseResult.okResult(category);
    }

    @PutMapping
    public ResponseResult<?> putCategory(@RequestBody Category category) {
        categoryService.updateById(category);
        return ResponseResult.okResult();
    }

    @DeleteMapping("/{id}")
    public ResponseResult<?> deleteCategory(@PathVariable("id") Long id) {
        categoryService.removeById(id);
        return ResponseResult.okResult();
    }

}
