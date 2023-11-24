package com.wz.blogcommon.handler;

import com.alibaba.fastjson.JSON;
import com.wz.blogcommon.result.AppHttpCodeEnum;
import com.wz.blogcommon.result.ResponseResult;
import com.wz.blogcommon.utils.WebUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wazh
 * @since 2023-10-14-10:24
 * 授权失败处理器
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        accessDeniedException.printStackTrace();
        ResponseResult<?> result = ResponseResult.errorResult(AppHttpCodeEnum.NO_OPERATOR_AUTH);
        //响应给前端
        System.out.println(result);
        WebUtils.renderString(httpServletResponse, JSON.toJSONString(result));
    }
}
