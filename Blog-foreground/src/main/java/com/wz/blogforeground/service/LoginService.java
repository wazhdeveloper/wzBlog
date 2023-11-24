package com.wz.blogforeground.service;

import com.wz.blogcommon.bean.User;
import com.wz.blogcommon.result.ResponseResult;

/**
 * @author wazh
 * @since 2023-10-13-14:06
 */
public interface LoginService {

    ResponseResult<?> login(User user);

    ResponseResult<?> logout();
}
