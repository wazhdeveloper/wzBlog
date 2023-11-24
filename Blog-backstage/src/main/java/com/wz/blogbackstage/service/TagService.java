package com.wz.blogbackstage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wz.blogcommon.bean.Tag;
import com.wz.blogcommon.bean.vo.PageTagVo;
import com.wz.blogcommon.result.ResponseResult;

import java.util.List;

/**
 * @author wazh
 * @since 2023-10-18-15:07
 */
public interface TagService extends IService<Tag> {

    ResponseResult<?> getTagList(Integer pageNum, Integer pageSize, PageTagVo pageTagVo);

    void addTag(PageTagVo pageTagVo);

    ResponseResult<?> deleteById(Long id);

    Tag getTagById(Long id);

    void editTag(Tag tag);

}
