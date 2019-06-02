package com.bigguy.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.swing.*;

/**
 * @author bigguy_hc
 * @create 2019-06-02 18:58
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @RequiresPermissions("admin:list")
    @ResponseBody
    @RequestMapping("/list")
    public String list(){
        return "list";
    }
}
