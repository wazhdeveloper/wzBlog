package com.wz.blogbackstage.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wz.blogbackstage.service.MenuService;
import com.wz.blogcommon.annotation.SystemLog;
import com.wz.blogcommon.bean.Menu;
import com.wz.blogcommon.bean.dto.MenuDto;
import com.wz.blogcommon.bean.dto.RoleMenuTreeDto;
import com.wz.blogcommon.result.AppHttpCodeEnum;
import com.wz.blogcommon.result.ResponseResult;
import com.wz.blogcommon.utils.BeanCopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import reactor.util.annotation.Nullable;

import java.util.List;

/**
 * @author wazh
 * @since 2023-10-24-21:12
 */
@RestController
@RequestMapping("/system/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/list")
    @SystemLog(businessName = "根据条件获取菜单")
    public ResponseResult<?> listMenu(
            @Nullable @RequestParam("status") String status,
            @Nullable @RequestParam("menuName") String menuName
    ) {
        List<Menu> menus = menuService.listMenu(status, menuName);
        return ResponseResult.okResult(menus);
    }

    @PostMapping
    @SystemLog(businessName = "添加菜单")
    public ResponseResult<?> postMenu(@RequestBody Menu menu) {
        menuService.save(menu);
        return ResponseResult.okResult();
    }

    @GetMapping("/{id}")
    @SystemLog(businessName = "根据id获取菜单")
    public ResponseResult<?> getMenu(@PathVariable("id") Long id) {
        Menu menu = menuService.getById(id);
        return ResponseResult.okResult(menu);
    }

    @PutMapping
    @SystemLog(businessName = "修改菜单")
    public ResponseResult<?> modifyMenu(@RequestBody Menu menu) {
        if (menu.getParentId().equals(menu.getId())) {
            return ResponseResult.errorResult(500, "修改菜单'写博文'失败，上级菜单不能选择自己");
        }
        menuService.updateById(menu);
        return ResponseResult.okResult();
    }

    @DeleteMapping("/{menuId}")
    @SystemLog(businessName = "根据id删除菜单")
    public ResponseResult<?> deleteMenu(@PathVariable("menuId") Long menuId) {
        //能够删除菜单，但是如果要删除的菜单有子菜单则提示：存在子菜单不允许删除 并且删除失败
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Menu::getParentId, menuId);
        List<Menu> menus = menuService.list(queryWrapper);
        if (menus.size() > 0) {
            return ResponseResult.errorResult(500, "存在子菜单不允许删除");
        }
        menuService.removeById(menuId);
        return ResponseResult.okResult();
    }

    @GetMapping("/treeselect")
    @SystemLog(businessName = "获得菜单树")
    public ResponseResult<?> treeSelect() {
        List<MenuDto> menuDtos = menuService.treeSelect();
        return ResponseResult.okResult(menuDtos);
    }

    @GetMapping("roleMenuTreeselect/{id}")
    public ResponseResult<?> roleMenuTreeselect(@PathVariable("id") Long id) {
        List<MenuDto> menuDtos = menuService.treeSelect();
        List<Long> selectedMenuIds = menuService.listSelectedMenuById(id);
        return ResponseResult.okResult(new RoleMenuTreeDto(menuDtos, selectedMenuIds));
    }
}
