package com.wz.blogforeground.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wz.blogcommon.bean.User;
import com.wz.blogcommon.bean.vo.UserInfoVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author wazh
 * @since 2023-10-08-16:36
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<User> getAll();

    User getUserById(Long userId);

    int register(User user);

    int updateUserInfo(User user);
}
