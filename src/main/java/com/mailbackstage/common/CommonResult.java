package com.mailbackstage.common;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Yan liang
 * @create 2019/6/7
 * @since 1.0.0
 */
@Data
@Getter
@Setter
public class CommonResult {
    public CommonResult() {
        this.Success = true;
    }

    public CommonResult success(boolean success, String message) {
        this.Success = success;
        this.Message = message;
        return this;
    }

    private boolean Success;
    private String Message;
}