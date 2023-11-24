package com.wz.blogbackstage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wz.blogbackstage.mapper.TagMapper;
import com.wz.blogbackstage.service.TagService;
import com.wz.blogcommon.bean.Tag;
import com.wz.blogcommon.bean.vo.PageTagVo;
import com.wz.blogcommon.bean.vo.PageVo;
import com.wz.blogcommon.result.ResponseResult;
import com.wz.blogcommon.utils.BeanCopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author wazh
 * @since 2023-10-18-15:07
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public ResponseResult<?> getTagList(Integer pageNum, Integer pageSize, PageTagVo pageTagVo) {
        LambdaQueryWrapper<Tag> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(StringUtils.hasText(pageTagVo.getName()),Tag::getName, pageTagVo.getName());
        lambdaQueryWrapper.eq(StringUtils.hasText(pageTagVo.getRemark()), Tag::getRemark, pageTagVo.getRemark());

        Page<Tag> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page, lambdaQueryWrapper);
        //封装数据返回
        PageVo pageVo = new PageVo(page.getRecords(), (long) page.getRecords().size());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public void addTag(PageTagVo pageTagVo) {
        Tag tag = BeanCopyUtil.copyBean(pageTagVo, Tag.class);
        TagMapper tagMapper = getBaseMapper();
        tagMapper.insert(tag);
    }

    @Override
    public ResponseResult<?> deleteById(Long id) {
        TagMapper tagMapper = getBaseMapper();
        LambdaQueryWrapper<Tag> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Tag::getId, id);

        int delete = tagMapper.delete(lambdaQueryWrapper);
        System.out.println(delete);
        return ResponseResult.okResult();
    }

    @Override
    public Tag getTagById(Long id) {
        TagMapper tagMapper = getBaseMapper();
        LambdaQueryWrapper<Tag> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Tag::getId, id);
        return tagMapper.selectOne(lambdaQueryWrapper);
    }

    @Override
    public void editTag(Tag tag) {
        TagMapper tagMapper = getBaseMapper();
        tagMapper.updateById(tag);
    }

}
