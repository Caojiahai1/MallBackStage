package com.mailbackstage.controller;

import com.mailbackstage.common.CommonResult;
import com.mailbackstage.model.UmsAdmin;
import com.mailbackstage.service.UmsAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yan liang
 * @create 2019/6/8
 * @since 1.0.0
 */
@Api(tags = "UmsAdminController", description = "后台用户管理")
@RestController
@RequestMapping("/admin")
public class UmsAdminController {
    @Autowired
    private UmsAdminService umsAdminService;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public CommonResult<UmsAdmin> register(@RequestBody UmsAdmin umsAdminParam) {
        UmsAdmin umsAdmin = umsAdminService.register(umsAdminParam);
        if (umsAdmin == null) {
            return new CommonResult<>().success(false, "注册失败");
        }
        return new CommonResult<UmsAdmin>().success(true, "注册成功", umsAdmin);
    }

    @ApiOperation(value = "登录后返回token")
    @PostMapping("/login")
    public CommonResult<Map<String, String>> login(@RequestParam("username") String username, @RequestParam("password") String password) {
        String token = umsAdminService.login(username, password);
        if (token == null) {
            return new CommonResult<>().success(false, "用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return new CommonResult<Map<String, String>>().success(true, "登录成功", tokenMap);
    }
}