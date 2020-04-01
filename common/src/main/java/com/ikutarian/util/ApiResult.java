package com.ikutarian.util;

import lombok.Getter;
import lombok.Setter;

/**
 * 自定义响应数据结构
 */
@Getter
@Setter
public class ApiResult {

    private Integer status = 200;
    private String msg = "OK";
    private Object data;

    public ApiResult() {
    }

    public ApiResult(Object data) {
        this.data = data;
    }

    public ApiResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public static ApiResult ok() {
        return new ApiResult(null);
    }

    public static ApiResult ok(Object data) {
        return new ApiResult(data);
    }

    public static ApiResult error(String msg) {
        return new ApiResult(500, msg, null);
    }
}
