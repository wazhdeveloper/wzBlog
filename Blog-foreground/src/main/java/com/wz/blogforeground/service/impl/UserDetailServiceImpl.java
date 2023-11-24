package com.wz.blogforeground.service.impl;

import com.wz.blogcommon.bean.LoginUser;
import com.wz.blogcommon.bean.Role;
import com.wz.blogcommon.bean.User;
import com.wz.blogforeground.mapper.UserDetailMapper;
import com.wz.blogforeground.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserDetailMapper userDetailMapper;

    @Autowired
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDetailMapper.getUserByUserName(s);
        if (Objects.isNull(user)) {
            throw new RuntimeException("用户不存在");
        }
        //返回用户信息
        return new LoginUser(user);
    }
}
