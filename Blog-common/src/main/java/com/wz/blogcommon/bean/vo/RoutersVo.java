package com.wz.blogcommon.bean.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author wazh
 * @since 2023-10-21-9:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoutersVo {

    List<MenuVo> menus;
}
