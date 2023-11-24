package com.wz.blogbackstage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wz.blogcommon.bean.Role;
import com.wz.blogcommon.bean.RoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wazh
 * @since 2023-10-19-9:37
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    List<String> getRoleKey(Long userId);

    void saveRoleMenu(RoleMenu roleMenu);

    void deleteRoleMenu(RoleMenu roleMenu);

    List<Long> getRoleIdsByUserId(Long userId);
}
