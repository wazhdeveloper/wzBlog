package com.wz.blogcommon.utils;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * @author wazh
 * @since 2023-10-16-21:03
 */
public class UpdateClassUtil {

    public static <T> T update(T object) {
        try {
            Date now = DateUtil.getNow();
            Long userId = SecurityUtils.getUserId();
            Class<?> objectClass = object.getClass();
            Field updateTime = objectClass.getDeclaredField("updateTime");
            Field updateBy = objectClass.getDeclaredField("updateBy");
            updateTime.setAccessible(true);
            updateBy.setAccessible(true);
            updateTime.set(object, now);
            updateBy.set(object, userId);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        return object;
    }
}
