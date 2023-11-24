package com.wz.blogbackstage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wz.blogcommon.bean.Link;
import com.wz.blogcommon.bean.vo.PageVo;

/**
 * @author wazh
 * @since 2023-10-26-19:50
 */
public interface LinkService extends IService<Link> {
    PageVo listLink(Integer pageNum, Integer pageSize, String name, String status);
}
