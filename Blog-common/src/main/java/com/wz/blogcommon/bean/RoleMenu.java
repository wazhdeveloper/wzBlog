package com.wz.blogcommon.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wazh
 * @since 2023-10-26-8:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleMenu {
    private Long roleId;
    private Long menuId;
}
