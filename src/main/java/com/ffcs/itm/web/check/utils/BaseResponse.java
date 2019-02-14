package com.ffcs.itm.web.check.utils;

/**
 * 返回消息型基类
 */
public class BaseResponse {
    /**
     * 消息状态
     */
    private int code;
    /**
     * 消息说明
     */
    private String msg;
    /**
     * 返回消息体
     */
    private Object responseBody;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(Object responseBody) {
        this.responseBody = responseBody;
    }
}
