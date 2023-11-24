package com.wz.blogforeground.mapper;

import com.wz.blogcommon.bean.Link;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author wazh
 * @since 2023-10-12-9:40
 */
@Mapper
public interface LinkMapper {

    List<Link> getAllLink();
}
