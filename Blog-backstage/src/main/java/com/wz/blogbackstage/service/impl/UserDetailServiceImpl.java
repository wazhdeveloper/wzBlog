package com.wz.blogbackstage.service.impl;

import com.wz.blogbackstage.mapper.MenuMapper;
import com.wz.blogbackstage.mapper.UserDetailMapper;
import com.wz.blogbackstage.service.MenuService;
import com.wz.blogbackstage.service.RoleService;
import com.wz.blogcommon.bean.LoginUser;
import com.wz.blogcommon.bean.Role;
import com.wz.blogcommon.bean.User;
import com.wz.blogcommon.utils.SystemConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author wazh
 * @since 2023-10-18-19:07
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private UserDetailMapper userDetailMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDetailMapper.getUserByUserName(s);
        if(Objects.isNull(user)){
            throw new RuntimeException("用户不存在");
        }
        if (user.getType().equals(SystemConstants.ADMIN)) {
            List<String> perms = menuMapper.getPerms(user.getId());
            //返回用户信息
            return new LoginUser(user, perms);
        }
        return new LoginUser(user);
    }
}
