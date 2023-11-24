package com.wz.blogforeground.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wz.blogcommon.bean.User;

/**
 * @author wazh
 * @since 2023-10-08-16:38
 */
public interface UserService extends IService<User> {
    User getUserById(Long id);

    void updateUserInfo(User user);

    void register(User user);
}
