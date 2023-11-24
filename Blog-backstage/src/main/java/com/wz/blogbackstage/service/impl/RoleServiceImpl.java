package com.wz.blogbackstage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wz.blogbackstage.mapper.RoleMapper;
import com.wz.blogbackstage.service.MenuService;
import com.wz.blogbackstage.service.RoleService;
import com.wz.blogcommon.bean.Role;
import com.wz.blogcommon.bean.RoleMenu;
import com.wz.blogcommon.bean.vo.PageVo;
import com.wz.blogcommon.result.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author wazh
 * @since 2023-10-19-9:38
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private MenuService menuService;

    @Override
    public List<Role> listAllRoles() {
        return roleMapper.selectList(null);
    }

    @Override
    public List<String> getRoleKeys(Long id) {
        return roleMapper.getRoleKey(id);
    }

    @Override
    public ResponseResult<?> listRoles(Integer pageNum, Integer pageSize, String status, String roleName) {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.hasText(status), Role::getStatus, status);
        queryWrapper.like(StringUtils.hasText(roleName), Role::getRoleName, roleName);
        queryWrapper.orderByAsc(Role::getRoleSort);

        Page<Role> page = new Page<>(pageNum, pageSize);
        page(page, queryWrapper);
        List<Role> records = page.getRecords();
        long total = page.getTotal();
        PageVo pageVo = new PageVo(records, total);
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public void saveRoleMenu(List<Long> menuIds, Long roleId) {
        List<Long> menuids = menuService.listSelectedMenuById(roleId);
        for (Long menuId : menuIds) { //基本数据类型比较的是内容
            if (menuids.contains(menuId)) {
                menuids.remove(menuId);
            } else {
                roleMapper.saveRoleMenu(new RoleMenu(roleId, menuId));
            }
            menuIds.remove(menuId);
        }
        if (menuids.size() > 0) for (Long menuid : menuids) roleMapper.deleteRoleMenu(new RoleMenu(roleId, menuid));
    }

    @Override
    public List<Long> getRoleIdsByUserId(Long userId) {
        return roleMapper.getRoleIdsByUserId(userId);
    }
}
