package com.wz.blogforeground.mapper;

import com.wz.blogcommon.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author wazh
 * @since 2023-10-13-15:03
 */
@Mapper
public interface UserDetailMapper {

    User getUserByUserName(@Param("userName") String userName);
}
