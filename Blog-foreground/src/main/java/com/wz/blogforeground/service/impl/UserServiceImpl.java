package com.wz.blogforeground.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wz.blogcommon.bean.User;
import com.wz.blogcommon.result.AppHttpCodeEnum;
import com.wz.blogcommon.utils.DateUtil;
import com.wz.blogcommon.exception.SystemException;
import com.wz.blogforeground.mapper.UserMapper;
import com.wz.blogforeground.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * @author wazh
 * @since 2023-10-08-16:39
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService  {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;
    /**
     * @param id 该用户的id
     * @return 返回指定id的用户
     */
    @Override
    public User getUserById(Long id) {
        if (!Objects.equals(id, 0L) && id != null) {
            return userMapper.getUserById(id);
        } else {
            return null;
        }
    }

    @Override
    public void updateUserInfo(User user) {
        userMapper.updateUserInfo(user);
    }

    @Override
    public void register(User user) {
        //对数据进行非空判断
        if(!StringUtils.hasText(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.USERNAME_NOT_NULL);
        }
        if(!StringUtils.hasText(user.getPassword())){
            throw new SystemException(AppHttpCodeEnum.PASSWORD_NOT_NULL);
        }
        if(!StringUtils.hasText(user.getEmail())){
            throw new SystemException(AppHttpCodeEnum.EMAIL_NOT_NULL);
        }
        if(!StringUtils.hasText(user.getNickName())){
            throw new SystemException(AppHttpCodeEnum.NICKNAME_NOT_NULL);
        }
        //对数据进行是否存在的判断
        if(userNameExist(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        }
        if(nickNameExist(user.getNickName())){
            throw new SystemException(AppHttpCodeEnum.NICKNAME_EXIST);
        }
        //...
        //对密码进行加密
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        user.setCreateTime(DateUtil.getNow());
        user.setUpdateTime(DateUtil.getNow());
        userMapper.register(user);
    }

    private boolean nickNameExist(String nickName) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("nick_name",nickName);
        User user = userMapper.selectOne(queryWrapper);
        return !Objects.isNull(user);
    }

    private boolean userNameExist(String userName) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("user_name",userName);
        User user = userMapper.selectOne(queryWrapper);
        return !Objects.isNull(user);
    }


}
