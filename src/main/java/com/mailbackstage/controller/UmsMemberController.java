package com.mailbackstage.controller;

import com.mailbackstage.common.CommonResult;
import com.mailbackstage.service.UmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Yan liang
 * @create 2019/6/7
 * @since 1.0.0
 */
@Api(tags = "UmsMemberController", description = "会员登录注册管理")
@RestController
@RequestMapping("/UmsMember")
public class UmsMemberController {

    @Autowired
    private UmsMemberService umsMemberService;

    @ApiOperation("获取验证码")
    @GetMapping("/getAuthCode/{telephone}")
    public CommonResult getAuthCode(@PathVariable("telephone") String telephone) {
        return umsMemberService.getAuthCode(telephone);
    }

    @ApiOperation("判断验证码是否正确")
    @PostMapping("/verifyAuthCode/{telephone}/{authCode}")
    public CommonResult verifyAuthCode(@PathVariable("telephone") String telephone,
                                        @PathVariable("authCode") String authCode) {
        return umsMemberService.verifyAuthCode(telephone, authCode);
    }
}