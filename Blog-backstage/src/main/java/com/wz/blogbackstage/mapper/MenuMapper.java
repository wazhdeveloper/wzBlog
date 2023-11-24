package com.wz.blogbackstage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wz.blogcommon.bean.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author wazh
 * @since 2023-10-20-9:57
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    List<String> getPerms(Long userId);
    List<String> getAllMenuType();
    List<Menu> getMenusByUserId(Long userId);
    List<Menu> selectAllRouterMenu();
    List<Menu> selectRouterMenuTreeByUserId(Long userId);

    List<Long> listSelectedMenuById(Long id);
}
