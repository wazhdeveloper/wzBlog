package com.wz.blogcommon.exception;

import com.wz.blogcommon.result.AppHttpCodeEnum;

/**
 * @author wazh
 * @since 2023-10-14-10:29
 */
public class SystemException extends RuntimeException {

    private int code;

    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public SystemException(AppHttpCodeEnum httpCodeEnum) {
        super(httpCodeEnum.getMsg());
        this.code = httpCodeEnum.getCode();
        this.msg = httpCodeEnum.getMsg();
    }

}