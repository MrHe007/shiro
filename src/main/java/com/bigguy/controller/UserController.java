package com.bigguy.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author bigguy_hc
 * @create 2019-06-02 15:43
 */

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/login")
    public String login(Model model, HttpServletRequest request){

        Subject subject = SecurityUtils.getSubject();
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        try{
            subject.login(token);
            subject.getSession().setAttribute("subject",subject);

            return "main";      //跳转到 main

        }catch (Exception e){
            System.out.println("登入 失败");
            request.setAttribute("errorMsg", "登入失败!");
            return "forward:/login.jsp";
        }
    }


    @RequiresPermissions("user:list")
    @RequestMapping("/list")
    @ResponseBody
    public List<String> userList(){
        List<String> list = new ArrayList<>();
        list.add("tom");
        list.add("hc");
        list.add("bigguy");
        list.add("admin");
        return list;
    }


}
