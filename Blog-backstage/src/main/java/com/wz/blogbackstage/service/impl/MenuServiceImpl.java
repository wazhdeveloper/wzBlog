package com.wz.blogbackstage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wz.blogbackstage.mapper.MenuMapper;
import com.wz.blogbackstage.service.MenuService;
import com.wz.blogcommon.bean.Menu;
import com.wz.blogcommon.bean.dto.MenuDto;
import com.wz.blogcommon.bean.vo.MenuVo;
import com.wz.blogcommon.utils.BeanCopyUtil;
import com.wz.blogcommon.utils.SecurityUtils;
import com.wz.blogcommon.utils.SystemConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wazh
 * @since 2023-10-20-10:00
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<String> getPerms(Long userId) {
        return menuMapper.getPerms(userId);
    }

    @Override
    public List<String> getAllMenuType() {
        return menuMapper.getAllMenuType();
    }

    @Override
    public List<Menu> getMenusByUserId() {
        Long userId = SecurityUtils.getUserId();
        return menuMapper.getMenusByUserId(userId);
    }

    @Override
    public List<MenuVo> selectRouterMenuTreeByUserId(Long userId) {
        List<Menu> menus = null;
        //判断是否是管理员
        if (SecurityUtils.isAdmin()) {
            //如果是 获取所有符合要求的Menu
            menus = menuMapper.selectAllRouterMenu();
        } else {
            //否则  获取当前用户所具有的Menu
            menus = menuMapper.selectRouterMenuTreeByUserId(userId);
        }
        List<MenuVo> menuVos = BeanCopyUtil.copyBeanList(menus, MenuVo.class);
        //构建tree
        //先找出第一层的菜单  然后去找他们的子菜单设置到children属性中
        return builderMenuTree(menuVos, SystemConstants.NORMAL_PARENT_ID);
    }

    @Override
    public List<Menu> listMenu(String status, String menuName) {
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(menuName), Menu::getMenuName, menuName);
        queryWrapper.eq(StringUtils.hasText(status), Menu::getStatus, status);
        queryWrapper.orderByDesc(Menu::getId);
        queryWrapper.orderByDesc(Menu::getOrderNum);

        return getBaseMapper().selectList(queryWrapper);
    }

    @Override
    public List<MenuDto> treeSelect() {
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Menu::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        // TODO 查询所有的菜单，并重新排列成父子顺序
        List<Menu> menus = menuMapper.selectList(queryWrapper);
        List<MenuDto> menuDtos = menus.stream().map(menu -> BeanCopyUtil.copyBean(menu, MenuDto.class).setLabel(menu.getMenuName())).collect(Collectors.toList());
        return builderMenuDtoTree(menuDtos, SystemConstants.NORMAL_PARENT_ID);
    }

    @Override
    public List<Long> listSelectedMenuById(Long id) {
        return menuMapper.listSelectedMenuById(id);
    }

    private List<MenuDto> builderMenuDtoTree(List<MenuDto> menus, Long parentId) {
        return menus.stream()
                .filter(menu -> menu.getParentId().equals(parentId))
                .map(menu -> menu.setChildren(getDtoChildren(menu, menus)))
                .collect(Collectors.toList());
    }

    private List<MenuDto> getDtoChildren(MenuDto menu, List<MenuDto> menus) {
        return menus.stream()
                .filter(m -> m.getParentId().equals(menu.getId()))
                .map(menuVo -> menuVo.setChildren(getDtoChildren(menuVo, menus)))
                .collect(Collectors.toList());
    }

    private List<MenuVo> builderMenuTree(List<MenuVo> menus, Long parentId) {
        return menus.stream()
                .filter(menu -> menu.getParentId().equals(parentId))
                .map(menu -> menu.setChildren(getChildren(menu, menus)))
                .collect(Collectors.toList());
    }

    /**
     * 获取存入参数的 子Menu集合
     *
     * @param menu
     * @param menus
     * @return
     */
    private List<MenuVo> getChildren(MenuVo menu, List<MenuVo> menus) {
        return menus.stream()
                .filter(m -> m.getParentId().equals(menu.getId()))
                .map(menuVo -> menuVo.setChildren(getChildren(menuVo, menus)))
                .collect(Collectors.toList());
    }


}
