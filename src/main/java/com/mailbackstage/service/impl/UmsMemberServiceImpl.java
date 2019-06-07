package com.mailbackstage.service.impl;

import com.mailbackstage.common.CommonResult;
import com.mailbackstage.common.redis.RedisService;
import com.mailbackstage.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Random;

/**
 * @author Yan liang
 * @create 2019/6/7
 * @since 1.0.0
 */
@Service
public class UmsMemberServiceImpl implements UmsMemberService{

    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;
    @Value("${redis.key.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS;
    @Autowired
    private RedisService redisService;

    @Override
    public CommonResult getAuthCode(String telephone) {
        CommonResult commonResult = new CommonResult();
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        redisService.set(REDIS_KEY_PREFIX_AUTH_CODE + telephone, sb.toString());
        redisService.expire(REDIS_KEY_PREFIX_AUTH_CODE + telephone, AUTH_CODE_EXPIRE_SECONDS);
        return commonResult.success(true, "获取验证码成功:" + sb.toString());
    }

    @Override
    public CommonResult verifyAuthCode(String telephone, String authCode) {
        CommonResult commonResult = new CommonResult();
        if (StringUtils.isEmpty(authCode)) {
            return commonResult.success(false, "验证码不能为空");
        }
        String cacheAuthCode = redisService.get(REDIS_KEY_PREFIX_AUTH_CODE + telephone);
        if (StringUtils.isEmpty(cacheAuthCode)) {
            return commonResult.success(false, "验证码已失效请重新获取");
        }
        if (authCode.equals(cacheAuthCode)) {
            return commonResult.success(true, "验证成功");
        } else {
            return commonResult.success(false, "验证码不正确");
        }
    }
}