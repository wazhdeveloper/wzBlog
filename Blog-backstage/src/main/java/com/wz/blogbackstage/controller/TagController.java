package com.wz.blogbackstage.controller;

import com.wz.blogbackstage.service.TagService;
import com.wz.blogcommon.annotation.SystemLog;
import com.wz.blogcommon.bean.Tag;
import com.wz.blogcommon.bean.vo.PageTagVo;
import com.wz.blogcommon.bean.vo.PageVo;
import com.wz.blogcommon.result.ResponseResult;
import com.wz.blogcommon.utils.BeanCopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wazh
 * @since 2023-10-18-15:08
 */
@RestController
@RequestMapping("/content/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/list")
    @SystemLog(businessName = "根据给定的页码，查询条件，获取指定标签信息")
    public ResponseResult<?> getTagList(Integer pageNum, Integer pageSize, PageTagVo ptv) {
        return tagService.getTagList(pageNum, pageSize, ptv);
    }

    @PostMapping
    @SystemLog(businessName = "添加标签")
    public ResponseResult<?> addTag(@RequestBody PageTagVo pageTagVo) {
        tagService.addTag(pageTagVo);
        return ResponseResult.okResult();
    }

    @DeleteMapping("/{id}")
    @SystemLog(businessName = "删除标签")
    public ResponseResult<?> deleteTag(@PathVariable Long id) {
        return tagService.deleteById(id);
    }

    @GetMapping("/{id}")
    @SystemLog(businessName = "修改标签-1-获取标签信息")
    public ResponseResult<?> getTagInfo(@PathVariable Long id) {
        Tag tag = tagService.getTagById(id);
        PageTagVo pageTagVo = BeanCopyUtil.copyBean(tag, PageTagVo.class);
        return ResponseResult.okResult(pageTagVo);
    }

    @PutMapping
    @SystemLog(businessName = "修改标签-2-提交修改内容")
    public ResponseResult<?> editTag(@RequestBody Tag tag) {
        tagService.editTag(tag);
        return ResponseResult.okResult();
    }

    @GetMapping("/listAllTag")
    @SystemLog(businessName = "获取所有标签")
    public ResponseResult<?> getAllTag() {
        List<?> rows = ((PageVo) tagService.getTagList(0, 1000, new PageTagVo()).getData()).getRows();
        return ResponseResult.okResult(rows);
    }
}
