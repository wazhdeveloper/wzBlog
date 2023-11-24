package com.wz.blogbackstage.service.impl;

import com.wz.blogcommon.utils.SecurityUtils;
import org.springframework.stereotype.Service;

/**
 * @author wazh
 * @since 2023-10-24-16:45
 */
@Service("ps")
public class PermissionService {

    /**
     * 检查该用户是否有该项权利
     * @param permission 检查是否拥有的权利
     * @return
     */
    public Boolean hasPermission(String permission) {
        return SecurityUtils.isAdmin() || SecurityUtils.getLoginUser().getPermission().contains(permission);
    }
}
