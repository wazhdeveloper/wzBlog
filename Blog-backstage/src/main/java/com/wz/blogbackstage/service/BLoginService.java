package com.wz.blogbackstage.service;

import com.wz.blogcommon.bean.User;
import com.wz.blogcommon.result.ResponseResult;

/**
 * @author wazh
 * @since 2023-10-18-19:01
 */
public interface BLoginService {
    ResponseResult<?> login(User user);
}
