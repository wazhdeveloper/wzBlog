package com.wz.blogforeground.controller;

import com.wz.blogcommon.annotation.SystemLog;
import com.wz.blogcommon.bean.User;
import com.wz.blogcommon.result.ResponseResult;
import com.wz.blogforeground.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wazh
 * @since 2023-10-13-14:04
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    @SystemLog(businessName = "登录")
    public ResponseResult<?> login(@RequestBody User user) {
        return loginService.login(user);
    }

    @PostMapping("/logout")
    @SystemLog(businessName = "登出")
    public ResponseResult<?> logout() {
        return loginService.logout();
    }
}
