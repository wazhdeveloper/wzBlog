package com.wz.blogbackstage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wz.blogbackstage.mapper.UserMapper;
import com.wz.blogbackstage.service.UserService;
import com.wz.blogcommon.bean.User;
import com.wz.blogcommon.bean.dto.UserRoleDto;
import com.wz.blogcommon.bean.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author wazh
 * @since 2023-10-26-13:14
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public PageVo listUser(Integer pageNum, Integer pageSize, String userName, String phonenumber, String status) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(userName), User::getUserName, userName);
        queryWrapper.eq(StringUtils.hasText(phonenumber), User::getPhonenumber, phonenumber);
        queryWrapper.eq(StringUtils.hasText(status), User::getStatus, status);

        Page<User> page = new Page<>(pageNum, pageSize);
        page(page, queryWrapper);
        List<User> records = page.getRecords();
        long total = page.getTotal();
        return new PageVo(records, total);
    }

    @Override
    public void addUserRole(UserRoleDto userRoleDto) {
        userMapper.addUserRole(userRoleDto);
    }

    @Override
    public Long maxUserId() {
        return userMapper.maxUserId();
    }

    @Override
    public List<Long> getRolesOfUser(Long id) {
        return userMapper.getRolesOfUser(id);
    }

    @Override
    public void saveUserRole(UserRoleDto userRoleDto) {
        userMapper.saveUserRole(userRoleDto);
    }

    @Override
    public void removeRoleOfUser(UserRoleDto userRoleDto) {
        userMapper.removeRoleOfUser(userRoleDto);
    }
}
