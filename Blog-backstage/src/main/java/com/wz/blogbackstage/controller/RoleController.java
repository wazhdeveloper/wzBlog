package com.wz.blogbackstage.controller;

import com.wz.blogbackstage.service.MenuService;
import com.wz.blogbackstage.service.RoleService;
import com.wz.blogcommon.annotation.SystemLog;
import com.wz.blogcommon.bean.Role;
import com.wz.blogcommon.bean.RoleMenu;
import com.wz.blogcommon.bean.dto.MenuDto;
import com.wz.blogcommon.bean.dto.RoleDto;
import com.wz.blogcommon.bean.vo.RoleVo;
import com.wz.blogcommon.result.ResponseResult;
import com.wz.blogcommon.utils.BeanCopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.util.annotation.Nullable;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author wazh
 * @since 2023-10-25-14:28
 */
@RestController
@RequestMapping("/system/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @GetMapping("/list")
    @SystemLog(businessName = "得到所有角色")
    public ResponseResult<?> listRole(
            @RequestParam("pageNum") Integer pageNum,
            @RequestParam("pageSize") Integer pageSize,
            @Nullable @RequestParam("status") String status,
            @Nullable @RequestParam("roleName") String roleName
    ) {
        return roleService.listRoles(pageNum, pageSize, status, roleName);
    }

    @PutMapping("/changeStatus")
    @SystemLog(businessName = "改变角色状态")
    public ResponseResult<?> changeStatus(@RequestBody RoleVo roleVo) {
        Role role = roleService.getById(roleVo.getRoleId());
        role.setStatus(roleVo.getStatus());
        roleService.updateById(role);
        return ResponseResult.okResult();
    }

    @PostMapping
    @SystemLog(businessName = "新增角色")
    public ResponseResult<?> postRole(@RequestBody Role role) {
        roleService.save(role);
        return ResponseResult.okResult();
    }

    @GetMapping("/{id}")
    @SystemLog(businessName = "根据ID获得角色信息")
    public ResponseResult<?> getRole(@PathVariable("id") Long id) {
        Role role = roleService.getById(id);
        return ResponseResult.okResult(role);
    }

    @PutMapping
    @SystemLog(businessName = "更新角色信息")
    public ResponseResult<?> updateRole(@RequestBody RoleDto roleDto) {
        Role role = BeanCopyUtil.copyBean(roleDto, Role.class);
        roleService.updateById(role);
        List<Long> menuIds = roleDto.getMenuIds();
        Long id = roleDto.getId();
        roleService.saveRoleMenu(menuIds, id);
        return ResponseResult.okResult();
    }

    @DeleteMapping("/{id}")
    public ResponseResult<?> deleteRole(@PathVariable("id") Long id) {
        roleService.removeById(id);
        return ResponseResult.okResult();
    }

    @GetMapping("/listAllRole")
    public ResponseResult<?> listAllRole() {
        List<Role> roles = roleService.listAllRoles();
        return ResponseResult.okResult(roles);
    }


}
