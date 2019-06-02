package com.bigguy.realm;

import com.bigguy.dao.MyUserDao;
import com.bigguy.dao.UserDaoUtils;
import com.bigguy.entity.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author bigguy_hc
 * @create 2019-06-02 16:04
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    MyUserDao userDao;

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

        UsernamePasswordToken userToken = (UsernamePasswordToken)token;
        String username = (String)userToken.getPrincipal();         // 假定 username 是user中的唯一标识

        User user = userDao.findUserByUsername(username);
        // 进行数据库查询，返回密码
        String password = user.getPassword();           // 数据库密码（加密的）
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, password, ByteSource.Util.bytes(username),getName());
        return info;
    }
}
