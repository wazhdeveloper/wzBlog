package com.wz.blogcommon.bean.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wazh
 * @since 2023-10-29-10:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserStatusDto {
    private Long userId;
    private String status;
}
