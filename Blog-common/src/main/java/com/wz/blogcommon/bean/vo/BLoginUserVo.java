package com.wz.blogcommon.bean.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author wazh
 * @since 2023-10-20-21:36
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BLoginUserVo {
    private List<String> permissions;

    private List<String> roles;

    private UserInfoVo user;
}
