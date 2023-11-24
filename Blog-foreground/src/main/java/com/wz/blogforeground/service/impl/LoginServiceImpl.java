package com.wz.blogforeground.service.impl;

import com.wz.blogcommon.bean.LoginUser;
import com.wz.blogcommon.bean.User;
import com.wz.blogcommon.bean.vo.UserInfoVo;
import com.wz.blogcommon.bean.vo.UserLoginVo;
import com.wz.blogcommon.result.ResponseResult;
import com.wz.blogcommon.utils.BeanCopyUtil;
import com.wz.blogcommon.utils.JwtUtil;
import com.wz.blogcommon.utils.RedisCache;
import com.wz.blogforeground.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;
    @Override
    public ResponseResult<?> login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //判断是否认证通过
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("用户名或密码错误");
        }
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        redisCache.setCacheObject("user:" + userId, loginUser);
        String jwt = JwtUtil.createJWT(userId);
        UserInfoVo userInfoVo = BeanCopyUtil.copyBean(loginUser.getUser(), UserInfoVo.class);
        UserLoginVo userLoginVo = new UserLoginVo(jwt, userInfoVo);
        return ResponseResult.okResult(userLoginVo);
    }

    @Override
    public ResponseResult<?> logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getId();
        redisCache.deleteObject("user:" + userId);
        return ResponseResult.okResult();
    }
}
