package com.wz.blogbackstage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wz.blogcommon.bean.Role;
import com.wz.blogcommon.result.ResponseResult;

import java.util.List;

/**
 * @author wazh
 * @since 2023-10-19-9:37
 */
public interface RoleService extends IService<Role> {

    List<String> getRoleKeys(Long id);

    ResponseResult<?> listRoles(Integer pageNum, Integer pageSize, String status, String roleName);

    void saveRoleMenu(List<Long> menuIds, Long roleId);

    List<Role> listAllRoles();

    List<Long> getRoleIdsByUserId(Long userId);
}
