package com.wz.blogforeground.controller;

import com.wz.blogcommon.annotation.SystemLog;
import com.wz.blogcommon.bean.User;
import com.wz.blogcommon.bean.vo.UserInfoVo;
import com.wz.blogcommon.result.ResponseResult;
import com.wz.blogcommon.utils.BeanCopyUtil;
import com.wz.blogcommon.utils.SecurityUtils;
import com.wz.blogcommon.utils.UpdateClassUtil;
import com.wz.blogforeground.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wazh
 * @since 2023-10-08-16:41
 */
@RestController("user")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/userInfo")
    @SystemLog(businessName = "获取用户信息")
    public ResponseResult<?> getUserInfo(@RequestParam("userId") Long userId) {
        User user = userService.getUserById(userId);
        UserInfoVo userInfoVo = BeanCopyUtil.copyBean(user, UserInfoVo.class);
        return ResponseResult.okResult(userInfoVo);
    }

    @PutMapping("/userInfo")
    @SystemLog(businessName = "更新用户信息")
    public ResponseResult<?> updateUserInfo(@RequestBody User user) {
        User updatedUser = UpdateClassUtil.update(user);
        userService.updateUserInfo(updatedUser);
        return ResponseResult.okResult();
    }

    @PostMapping("/register")
    @SystemLog(businessName = "用户注册")
    public ResponseResult<?> register(@RequestBody User user) {
        userService.register(user);
        return ResponseResult.okResult();
    }
}
