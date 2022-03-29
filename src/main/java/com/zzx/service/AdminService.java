package com.zzx.service;

import com.zzx.domain.Admin;

/**
 * @author ZZX
 * @date 2022/1/25 17:37
 */
public interface AdminService {
    /**
     * 验证登录
     * @param name
     * @param pwd
     * @return
     */
    Admin login(String name,String pwd);

    /**
     * 用户注册
     * @param adminName
     * @param adminPwd
     */
    int reg(String adminName, String adminPwd);
}
