package com.bigguy.realm;

import com.bigguy.dao.UserDaoUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author bigguy_hc
 * @create 2019-06-02 16:04
 */
public class UserRealm extends AuthorizingRealm {


    @Override
    public String getName() {
        return "userRealm";
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        String username = (String)principals.getPrimaryPrincipal();

        List<String> roles = UserDaoUtils.getRoles(username);
        List<String> permissions = UserDaoUtils.getPermissions(username);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(roles);
        info.addStringPermissions(permissions);

        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        UsernamePasswordToken user = (UsernamePasswordToken)token;
        String username = (String)user.getPrincipal();
        String password = new String(user.getPassword());

        SimpleAuthenticationInfo info = null;

        if("admin".equals(username) && UserDaoUtils.getPass(username).equals(password)){
            info  = new SimpleAuthenticationInfo(username, password, getName());
        }else if("tom".equals(username) &&UserDaoUtils.getPass(username).equals(password)){
            info  = new SimpleAuthenticationInfo(username, password, getName());
        }

        return info;
    }
}
