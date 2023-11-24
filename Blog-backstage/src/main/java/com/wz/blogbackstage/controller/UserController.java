package com.wz.blogbackstage.controller;

import com.wz.blogbackstage.service.RoleService;
import com.wz.blogbackstage.service.UserService;
import com.wz.blogcommon.bean.Role;
import com.wz.blogcommon.bean.User;
import com.wz.blogcommon.bean.dto.UserDto;
import com.wz.blogcommon.bean.dto.UserRoleDto;
import com.wz.blogcommon.bean.dto.UserStatusDto;
import com.wz.blogcommon.bean.vo.PageVo;
import com.wz.blogcommon.bean.vo.UserVo;
import com.wz.blogcommon.result.ResponseResult;
import com.wz.blogcommon.utils.BeanCopyUtil;
import com.wz.blogcommon.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.util.annotation.Nullable;

import java.util.List;

/**
 * @author wazh
 * @since 2023-10-26-13:10
 */
@RestController
@RequestMapping("/system/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/list")
    public ResponseResult<?> listUser(
            @RequestParam("pageNum") Integer pageNum,
            @RequestParam("pageSize") Integer pageSize,
            @Nullable @RequestParam("userName") String userName,
            @Nullable @RequestParam("phonenumber") String phonenumber,
            @Nullable @RequestParam("status") String status
    ) {
        PageVo pageVo = userService.listUser(pageNum, pageSize, userName, phonenumber, status);
        return ResponseResult.okResult(pageVo);
    }

    @PostMapping
    public ResponseResult<?> addUser(@RequestBody UserDto userDto) {
        List<Long> roleIds = userDto.getRoleIds();
        User user = BeanCopyUtil.copyBean(userDto, User.class);
        userService.save(user);
        Long userId = userService.maxUserId();
        for (Long roleId : roleIds) {
            userService.addUserRole(new UserRoleDto(userId, roleId));
        }
        return ResponseResult.okResult();
    }

    @DeleteMapping("/{id}")
    public ResponseResult<?> deleteUserById(@PathVariable("id") Long id) {
        if (SecurityUtils.getUserId().equals(id)) {
            return ResponseResult.errorResult(500, "不能删除当前用户（该用户正在操作）");
        }
        userService.removeById(id);
        return ResponseResult.okResult();
    }

    @GetMapping("/{id}")
    public ResponseResult<?> modifyUser(@PathVariable("id") Long id) {
        List<Role> roles = roleService.listAllRoles();
        List<Long> roleIds = roleService.getRoleIdsByUserId(id);
        User user = userService.getById(id);
        return ResponseResult.okResult(new UserVo(roleIds, roles, user));
    }

    @PutMapping
    public ResponseResult<?> updateUser(@RequestBody UserDto userDto) {
        List<Long> roleIds = userDto.getRoleIds();
        User user = BeanCopyUtil.copyBean(userDto, User.class);
        userService.updateById(user);
        Long id = user.getId();
        List<Long> rolesOfUser = userService.getRolesOfUser(id);
        for (Long roleId : roleIds) {
            if (!rolesOfUser.contains(roleId)) {
                userService.saveUserRole(new UserRoleDto(id, roleId));
            } else {
                rolesOfUser.remove(roleId);
            }
            roleIds.remove(roleId);
        }
        if (rolesOfUser.size() > 0) for (Long userRole : rolesOfUser) userService.removeRoleOfUser(new UserRoleDto(id, userRole));
        return ResponseResult.okResult();
    }

    @PutMapping("/changeStatus")
    public ResponseResult<?> changeStatus(@RequestBody UserStatusDto user) {
        Long id = user.getUserId();
        User tUser = userService.getById(id);
        tUser.setStatus(user.getStatus());
        userService.updateById(tUser);
        return ResponseResult.okResult();
    }
}
