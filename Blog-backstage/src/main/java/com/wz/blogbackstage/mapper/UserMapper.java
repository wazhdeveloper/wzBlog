package com.wz.blogbackstage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wz.blogcommon.bean.User;
import com.wz.blogcommon.bean.dto.UserRoleDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author wazh
 * @since 2023-10-26-13:14
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    void addUserRole(UserRoleDto userRoleDto);

    Long maxUserId();

    List<Long> getRolesOfUser(Long userId);

    void saveUserRole(UserRoleDto userRoleDto);

    void removeRoleOfUser(UserRoleDto userRoleDto);
}
