package com.wz.blogbackstage.controller;

import com.wz.blogbackstage.dao.MenuDao;
import com.wz.blogbackstage.service.BLoginService;
import com.wz.blogbackstage.service.MenuService;
import com.wz.blogbackstage.service.RoleService;
import com.wz.blogcommon.annotation.SystemLog;
import com.wz.blogcommon.bean.User;
import com.wz.blogcommon.bean.vo.BLoginUserVo;
import com.wz.blogcommon.bean.vo.MenuVo;
import com.wz.blogcommon.bean.vo.RoutersVo;
import com.wz.blogcommon.bean.vo.UserInfoVo;
import com.wz.blogcommon.exception.SystemException;
import com.wz.blogcommon.result.AppHttpCodeEnum;
import com.wz.blogcommon.result.ResponseResult;
import com.wz.blogcommon.utils.BeanCopyUtil;
import com.wz.blogcommon.utils.RedisCache;
import com.wz.blogcommon.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @author wazh
 * @since 2023-10-18-18:59
 */
@RestController
public class BLoginController {

    @Autowired
    private BLoginService bLoginService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RedisCache redisCache;

    @PostMapping("/user/login")
    @SystemLog(businessName = "后台用户登录")
    public ResponseResult<?> login(@RequestBody User user) {
        if (!StringUtils.hasText(user.getUserName())) {
            throw new SystemException(AppHttpCodeEnum.USERNAME_NOT_NULL);
        }
        return bLoginService.login(user);
    }

    @PostMapping("/user/logout")
    @SystemLog(businessName = "用户退出登录")
    public ResponseResult<?> logout() {
        Long userId = SecurityUtils.getUserId();
        redisCache.deleteObject("backLogin:" + userId);
        return ResponseResult.okResult();
    }

    @GetMapping("/getInfo")
    @SystemLog(businessName = "获取用户权限信息")
    public ResponseResult<?> getInfo() {
        Long userId = SecurityUtils.getUserId();
        List<String> perms = userId == 1 ? menuService.getAllMenuType() : menuService.getPerms(userId);
        List<String> roleKeys = roleService.getRoleKeys(userId);
        User user = SecurityUtils.getLoginUser().getUser();
        UserInfoVo infoVo = BeanCopyUtil.copyBean(user, UserInfoVo.class);
        BLoginUserVo bLoginUserVo = new BLoginUserVo(perms, roleKeys, infoVo);
        return ResponseResult.okResult(bLoginUserVo);
    }

    @GetMapping("/getRouters")
    @SystemLog(businessName = "获取路由信息")
    public ResponseResult<?> getRouters() {
        Long userId = SecurityUtils.getUserId();
        //查询menu 结果是tree的形式
        List<MenuVo> menus = menuService.selectRouterMenuTreeByUserId(userId);
        //封装数据返回
        return ResponseResult.okResult(new RoutersVo(menus));
    }
}
