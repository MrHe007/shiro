package com.bigguy.dao;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @author bigguy_hc
 * @create 2019-06-02 9:44
 */
public class UserDaoUtils {

    public static String getPass(String username){

        if("tom".equals(username)){
            return "tom123";
        }else if("admin".equals(username)){
            return "admin123";
        }
        return null;
    }

    public static List<String> getRoles(String username){

        ArrayList<String> list = new ArrayList<>();
        if("tom".equals(username)){
            list.add("user");        // 普通用户
        }else if("admin".equals(username)){
            list.add("admin");       // 管理员

        }

        return list;
    }

    public static  List<String> getPermissions(String username){
        ArrayList<String> list = new ArrayList<>();
        if("tom".equals(username)){
            list.add("movie:list");        // 只有查看的权限
        }else if("admin".equals(username)){
            list.add("movie:*");            // 操作电源所有权限
            list.add("user:*");             // 操作用户所有的权限
            list.add("prot:*");       // 管理员
            list.add("employee:*");       // 管理员
            list.add("deptment:*");       // 管理员
        }
        return list;
    }

}
