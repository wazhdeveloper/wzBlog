package com.wz.blogcommon.bean.vo;

import com.wz.blogcommon.bean.Role;
import com.wz.blogcommon.bean.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author wazh
 * @since 2023-10-26-14:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {
    private List<Long> roleIds;
    private List<Role> roles;
    private User user;
}
