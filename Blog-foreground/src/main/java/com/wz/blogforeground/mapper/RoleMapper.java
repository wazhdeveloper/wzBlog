package com.wz.blogforeground.mapper;

import com.wz.blogcommon.bean.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author wazh
 * @since 2023-10-19-9:37
 */
@Mapper
public interface RoleMapper {
    List<Role> getRoles(Long userId);
}
