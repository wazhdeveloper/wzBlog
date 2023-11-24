package com.wz.blogbackstage.service.impl;

import com.wz.blogbackstage.service.BLoginService;
import com.wz.blogcommon.bean.LoginUser;
import com.wz.blogcommon.bean.User;
import com.wz.blogcommon.bean.vo.UserInfoVo;
import com.wz.blogcommon.bean.vo.UserLoginVo;
import com.wz.blogcommon.result.ResponseResult;
import com.wz.blogcommon.utils.BeanCopyUtil;
import com.wz.blogcommon.utils.JwtUtil;
import com.wz.blogcommon.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

/**
 * @author wazh
 * @since 2023-10-18-19:02
 */
@Service
public class BLoginServiceImpl implements BLoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult<?> login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //判断是否认证通过
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("用户名或密码错误");
        }
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        redisCache.setCacheObject("backLogin:" + userId, loginUser);
        String jwt = JwtUtil.createJWT(userId);
        HashMap<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", jwt);
        return ResponseResult.okResult(tokenMap);
    }
}
