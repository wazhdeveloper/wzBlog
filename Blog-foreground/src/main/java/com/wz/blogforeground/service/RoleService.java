package com.wz.blogforeground.service;

import com.wz.blogcommon.bean.Role;

import java.util.List;

/**
 * @author wazh
 * @since 2023-10-19-9:37
 */
public interface RoleService {

    List<Role> getRoles(Long id);
}
