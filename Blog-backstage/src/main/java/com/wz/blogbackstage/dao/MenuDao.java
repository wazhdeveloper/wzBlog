package com.wz.blogbackstage.dao;

import com.wz.blogbackstage.service.MenuService;
import com.wz.blogcommon.bean.Menu;
import com.wz.blogcommon.bean.vo.MenuVo;
import com.wz.blogcommon.utils.BeanCopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wazh
 * @since 2023-10-20-20:53
 */
@Component
public class MenuDao {

    @Autowired
    private MenuService menuService;

    // useless
    @Deprecated
    public List<MenuVo> getRouters() {
        List<Menu> menus = menuService.getMenusByUserId();
        List<MenuVo> menuVos = BeanCopyUtil.copyBeanList(menus, MenuVo.class);
        //TODO 需要将menuVos集合转换成父子模式集合

        return null;
    }


}
