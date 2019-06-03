package com.bigguy.controller;

import com.bigguy.anno.PermissionNameAnno;
import com.bigguy.dao.PermissionDao;
import com.bigguy.entity.Permission;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.swing.*;
import java.util.Collection;
import java.util.Map;

/**
 * @author bigguy_hc
 * @create 2019-06-02 18:58
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private RequestMappingHandlerMapping rmhm;

    @Autowired
    PermissionDao permissionDao;


    @RequiresPermissions("admin:list")
    @ResponseBody
    @RequestMapping("/list")
    public String list(){
        return "list";
    }


    @ResponseBody
    @RequiresRoles("admin")
    @RequestMapping("/reload")
    public String reload(){

        // 但是在加入之前，可以先从数据库中获取权限表，以便去除重复的添加值


        Map<RequestMappingInfo, HandlerMethod> handlerMethods = rmhm.getHandlerMethods();
        Collection<HandlerMethod> values = handlerMethods.values();

        for (HandlerMethod method : values) {
            RequiresPermissions anno = method.getMethodAnnotation(RequiresPermissions.class);

            if(anno != null){
                PermissionNameAnno methodAnnotation = method.getMethodAnnotation(PermissionNameAnno.class);
                if(methodAnnotation !=null){        // 如果拿到权限名不为空
                    String resource = anno.value()[0];      // 拿到第一个值
                    Permission p = new Permission();
                    p.setName(methodAnnotation.value());    // 拿到注解上面的方法权限名
                    p.setResource(resource);
                    permissionDao.save(p);      // 保存
                }
            }

        }
        return "ok";
    }


}
