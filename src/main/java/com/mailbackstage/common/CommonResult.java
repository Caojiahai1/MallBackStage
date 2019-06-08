package com.mailbackstage.common;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Yan liang
 * @create 2019/6/7
 * @since 1.0.0
 */
@Data
@Getter
@Setter
@ToString
public class CommonResult<T> {
    public CommonResult() {
        this.success = true;
        this.code = ResultCode.SUCCESS.getCode();
    }

    public CommonResult success(boolean success, String message) {
        this.success = success;
        this.message = message;
        this.code = success ? ResultCode.SUCCESS.getCode() : ResultCode.FAILED.getCode();
        return this;
    }

    public CommonResult<T> success(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.code = success ? ResultCode.SUCCESS.getCode() : ResultCode.FAILED.getCode();
        this.data = data;
        return this;
    }

    public CommonResult<T> forbidden(T data) {
        this.code = ResultCode.FORBIDDEN.getCode();
        this.success = false;
        this.message = ResultCode.FORBIDDEN.getMessage();
        this.data = data;
        return this;
    }

    private boolean success;
    private String message;
    private long code;
    private T data;
}