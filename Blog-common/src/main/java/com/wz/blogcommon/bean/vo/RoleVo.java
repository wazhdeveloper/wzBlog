package com.wz.blogcommon.bean.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wazh
 * @since 2023-10-25-14:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleVo {
    private Long roleId;
    private String status;
}
