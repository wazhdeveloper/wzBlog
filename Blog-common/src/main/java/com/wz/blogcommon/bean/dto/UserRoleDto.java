package com.wz.blogcommon.bean.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wazh
 * @since 2023-10-26-13:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleDto {

    private Long userId;
    private Long roleId;
}
