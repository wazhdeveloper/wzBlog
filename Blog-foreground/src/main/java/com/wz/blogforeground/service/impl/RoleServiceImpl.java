package com.wz.blogforeground.service.impl;

import com.wz.blogcommon.bean.Role;
import com.wz.blogforeground.mapper.RoleMapper;
import com.wz.blogforeground.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wazh
 * @since 2023-10-19-9:38
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> getRoles(Long id) {
        return roleMapper.getRoles(id);
    }
}
