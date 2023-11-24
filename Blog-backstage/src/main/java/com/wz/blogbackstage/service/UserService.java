package com.wz.blogbackstage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wz.blogcommon.bean.User;
import com.wz.blogcommon.bean.dto.UserRoleDto;
import com.wz.blogcommon.bean.vo.PageVo;

import java.util.List;

/**
 * @author wazh
 * @since 2023-10-26-13:13
 */
public interface UserService extends IService<User> {
    PageVo listUser(Integer pageNum, Integer pageSize, String userName, String phonenumber, String status);

    void addUserRole(UserRoleDto userRoleDto);

    Long maxUserId();

    List<Long> getRolesOfUser(Long id);

    void saveUserRole(UserRoleDto userRoleDto);

    void removeRoleOfUser(UserRoleDto userRoleDto);
}
