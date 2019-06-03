package com.bigguy.controller;

import com.bigguy.anno.PermissionNameAnno;
import com.bigguy.entity.User;
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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author bigguy_hc
 * @create 2019-06-02 15:43
 */

@Controller
public class UserController {

    @RequestMapping("/login")
    public String login(Model model, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String exceptionClassName = (String) req.getAttribute("shiroLoginFailure");
        //根据shiro返回的异常类路径判断，抛出指定异常信息
        if(exceptionClassName!=null){
            if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
                //最终会抛给异常处理器
                req.setAttribute("errorMsg","账号不存在");
            } else if (IncorrectCredentialsException.class.getName().equals(
                    exceptionClassName)) {
                req.setAttribute("errorMsg","用户名/密码错误");
            } else {
                req.setAttribute("errorMsg","其他异常信息");//最终在异常处理器生成未知错误
            }
        }
        //此方法不处理登陆成功（认证成功），shiro认证成功会自动跳转到上一个请求路径
        //登陆失败还到login页面

        return "forward:/login.jsp";


    }


    @PermissionNameAnno("查询用户列表")
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

    @PermissionNameAnno("查询用户")
    @RequiresPermissions("user:get")
    @RequestMapping("/get/{id}")
    @ResponseBody
    public User getUser(Integer id){
        return null;
    }

    @PermissionNameAnno("修改用户")
    @RequiresPermissions("user:update")
    @RequestMapping("/update/{id}")
    @ResponseBody
    public User updateUser(Integer id){
        return null;
    }

    @PermissionNameAnno("增加用户")
    @RequiresPermissions("user:add")
    @RequestMapping("/add/{id}")
    @ResponseBody
    public User addUser(Integer id){
        return null;
    }

    @PermissionNameAnno("删除用户")
    @RequiresPermissions("user:delete")
    @RequestMapping("/delete/{id}")
    @ResponseBody
    public User delUser(Integer id){
        return null;
    }

}
