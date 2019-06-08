package com.mailbackstage.service;

import com.mailbackstage.model.UmsAdmin;
import com.mailbackstage.model.UmsPermission;

import java.util.List;

/**
 * @author Yan liang
 * @create 2019/6/8
 * @since 1.0.0
 */
public interface UmsAdminService {
    /**
     *根据用户名获取后台管理员
     *@params  [username]
     *@return  com.mailbackstage.model.UmsAdmin
     *@creater  yanliang
     *@createdate  2019/6/8
     *@info
     */
    UmsAdmin getAdminByUsername(String username);

    /**
     * 根据用户名获取后台管理员
     */
    UmsAdmin register(UmsAdmin umsAdmin);

    /**
     * 登录功能
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    String login(String username, String password);

    /**
     * 获取用户所有权限（包括角色权限和+-权限）
     */
    List<UmsPermission> getPermissionList(Long adminId);
}