package com.wz.blogbackstage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wz.blogcommon.bean.Menu;
import com.wz.blogcommon.bean.dto.MenuDto;
import com.wz.blogcommon.bean.vo.MenuVo;

import java.util.List;

/**
 * @author wazh
 * @since 2023-10-20-9:59
 */
public interface MenuService extends IService<Menu> {

    List<String> getPerms(Long userId);

    List<String> getAllMenuType();

    List<Menu> getMenusByUserId();

    List<MenuVo> selectRouterMenuTreeByUserId(Long userId);

    List<Menu> listMenu(String status, String menuName);

    List<MenuDto> treeSelect();

    List<Long> listSelectedMenuById(Long id);
}
