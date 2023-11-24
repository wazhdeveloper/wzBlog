package com.wz.blogcommon.bean.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author wazh
 * @since 2023-10-25-21:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleMenuTreeDto {
    private List<MenuDto> menus;
    private List<Long> checkedKeys;
}
