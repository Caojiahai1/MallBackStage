package com.mailbackstage.service;

import com.mailbackstage.common.CommonResult;

/**
 * @author Yan liang
 * @create 2019/6/7
 * @since 1.0.0
 */
public interface UmsMemberService {
    /**
     * 生成验证码
     */
    CommonResult getAuthCode(String telephone);

    /**
     * 判断验证码是否存在
     */
    CommonResult verifyAuthCode(String telephone, String authCode);
}