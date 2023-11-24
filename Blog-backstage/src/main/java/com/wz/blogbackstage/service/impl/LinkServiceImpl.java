package com.wz.blogbackstage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wz.blogbackstage.mapper.LinkMapper;
import com.wz.blogbackstage.service.LinkService;
import com.wz.blogcommon.bean.Link;
import com.wz.blogcommon.bean.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author wazh
 * @since 2023-10-26-19:50
 */
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    @Autowired
    private LinkMapper linkMapper;

    @Override
    public PageVo listLink(Integer pageNum, Integer pageSize, String name, String status) {
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(name), Link::getName, name);
        queryWrapper.eq(StringUtils.hasText(status), Link::getStatus, status);

        Page<Link> page = new Page<>(pageNum, pageSize);
        page(page, queryWrapper);
        List<Link> records = page.getRecords();
        long total = page.getTotal();
        return new PageVo(records, total);
    }
}
