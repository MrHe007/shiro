package com.bigguy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bigguy_hc
 * @create 2019-06-02 15:43
 */

@Controller
@RequestMapping("/user")
public class UserController {

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
