package com.guilin.shiro_mysql_integrate;

import com.guilin.shiro_mysql_integrate.dao.UserDao;
import com.guilin.shiro_mysql_integrate.mapper.RoleMapper;
import com.guilin.shiro_mysql_integrate.model.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("authorizer")
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserDao userdao;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = userdao.findUserByUsername(principalCollection.toString());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(user.getRoleNames());
        info.setStringPermissions(roleMapper.getPermissionsByRoleId(user.getRole().getId()));
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = userdao.findUserByUsername(token.getUsername());

        if (user == null) {
            throw new UnknownAccountException();
        }

        return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
    }
}