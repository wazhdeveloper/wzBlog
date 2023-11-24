package com.wz.blogforeground.service.impl;

import com.wz.blogcommon.bean.Link;
import com.wz.blogforeground.mapper.LinkMapper;
import com.wz.blogforeground.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wazh
 * @since 2023-10-12-9:39
 */
@Service
public class LinkServiceImpl implements LinkService {

    @Autowired
    private LinkMapper linkMapper;

    @Override
    public List<Link> getAllLink() {
        return linkMapper.getAllLink();
    }
}
