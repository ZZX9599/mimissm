package com.zzx.service.impl;

import com.zzx.domain.Admin;
import com.zzx.domain.AdminExample;
import com.zzx.mapper.AdminMapper;
import com.zzx.service.AdminService;
import com.zzx.utils.MD5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ZZX
 * @date 2022/1/25 17:37
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminMapper adminMapper;

    Admin admin=null;
    @Override

    public Admin login(String name, String pwd) {
        /**
         * 在使用逆向工程的mapper的时候，如果查询有条件，一定要创建AdminExample对象来封装条件
         */
        //加密
        pwd= MD5Util.getMD5(pwd);
        AdminExample example=new AdminExample();
        //如何添加条件  select * from admin where a_name='zzx'
        example.createCriteria().andANameEqualTo(name);
        List<Admin> adminList=adminMapper.selectByExample(example);
        System.out.println(adminList.size());
        if(adminList.size()>0){
            admin=adminList.get(0);
            if(admin.getaPass().equals(pwd)){
                return admin;
            }else {
                admin=null;
            }
        }
        return admin;
    }

    @Override
    public int reg(String adminName, String adminPwd) {
        //加密
        adminPwd=MD5Util.getMD5(adminPwd);
        System.out.println(adminName+""+adminPwd);
        Admin admin=new Admin();
        admin.setaName(adminName);
        admin.setaPass(adminPwd);
        return adminMapper.insert(admin);
    }
}
