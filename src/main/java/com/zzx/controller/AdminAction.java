package com.zzx.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzx.domain.Admin;
import com.zzx.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ZZX
 * @date 2022/1/25 17:37
 */
@Controller
@RequestMapping("/admin")
public class AdminAction {
    @Resource
    private AdminService adminService;

    /**
     * 登陆验证并跳转
     */
    @RequestMapping("/login")
    public String login(String name, String pwd, HttpServletRequest request){
        //调用service
        Admin admin=adminService.login(name,pwd);
        if(admin!=null){
            //登陆成功
            request.setAttribute("admin",admin);
            return "main";
        }else {
            //登陆失败
            request.setAttribute("errmsg","用户名或密码不正确");
            return "login";
        }
    }

    @RequestMapping("/reg")
    public void reg(String adminName, String adminPwd, HttpServletResponse response) {
        int result=adminService.reg(adminName,adminPwd);
        if(result==1) {
            Map<String, Boolean> map = new HashMap<>(10);
            map.put("success", true);
            ObjectMapper om = new ObjectMapper();
            try {
                String json = om.writeValueAsString(map);
                response.getWriter().print(json);
            } catch (Exception e) {
                String errMsg = e.getMessage();
                Map errMap=new HashMap(10);
                errMap.put("success",false);
                errMap.put("errMsg",errMsg);
                try {
                    String json=om.writeValueAsString(errMap);
                    response.getWriter().print(json);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
