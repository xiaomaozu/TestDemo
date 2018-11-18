package com.zxy.test.shiro.realm;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
/*
 * description:apache的shiro中提供了一些线程的realm接口，这里说一下AuthorizingRealm这个类。在用户登录的时候，
 * 一般我们的系统要对用户进行认证和授权两个最进本的处理，
 * 在shiro中的所有的登录、授权处理都会交给一个realm处理，所以处理流程都是先认证然后授权，AuthorizingRealm授权类继承
 * 了AuthenticatingRealm认证类，所以在AuthorizingRealm类中有一个继承来的doGetAuthenticationInfo方法，一个自身的doGetAuthorzationInfo方法，
 * 在实际项目使用中我们的项目就是使用AuthorzingRealm这个类的实现类来自定义认证和授权的处理过程，在web项目中使用自定义的类继承AuthorzingRealm类，
 * 实现doGetAuthentcationInfo方法实现用户登录时的认证逻辑；
 * 实现doGetAuthrozitionInfo方法实现用户的授权逻辑；
 * 在与spring整合项目中，shiro的SecurityManager会自动调用这两个方法，从而实现认证和授权，
 * 可以结合shiro的CacheManager将认证和授权信息保存在缓存中，这样可以提高系统的处理效率。
 * @author 曾鑫曜 (xinyao.zeng@ucarinc.com)
 * @date 2018/9/30 14:39
 * @param  
 * @return 
 **/
//AuthorizingRealm 实现认证和授权
public class ShiroRealm extends AuthorizingRealm {


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        System.out.println("ShiroRealm doGetAuthenticationInfo");
        //1. 把 AuthenticationToken 转换为 UsernamePasswordToken
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;

        //2. 从 UsernamePasswordToken 中来获取 username
        String username = upToken.getUsername();

        //3. 调用数据库的方法, 从数据库中查询 username 对应的用户记录
        System.out.println("从数据库中获取 username: " + username + " 所对应的用户信息.");

        //4. 若用户不存在, 则可以抛出 UnknownAccountException 异常
        if("unknown".equals(username)){
            throw new UnknownAccountException("用户不存在!");
        }

        //5. 根据用户信息的情况, 决定是否需要抛出其他的 AuthenticationException 异常.
        if("monster".equals(username)){
            throw new LockedAccountException("用户被锁定");
        }

        //6. 根据用户的情况, 来构建 AuthenticationInfo 对象并返回. 通常使用的实现类为: SimpleAuthenticationInfo
        //以下信息是从数据库中获取的.
        //1). principal(主要的，最重要的): 认证的实体信息. 可以是 username, 也可以是数据表对应的用户的实体类对象.
        Object principal = username;
        //2). credentials(证书证件): 密码.
        Object credentials = null;

        //"fc1709d0a95a6be30bc5926fdb7f22f4";
        if("admin".equals(username)){

            credentials = "038bdaf98f2037b31f1e75b5b4c9b26e";
        }else if("user".equals(username)){
            credentials = "098d2c478e9c11555ce2823231e02ec1";
        }

        //3). realmName: 当前 realm 对象的 name. 调用父类的 getName() 方法即可
        String realmName = getName();
        //4). 盐值.
        ByteSource credentialsSalt = ByteSource.Util.bytes(username);

       SimpleAuthenticationInfo info = null;//new SimpleAuthenticationInfo(principal, credentials, realmName);
        info = new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);
        return info;

    }

    public static void main(String[] args) {
        String hashAlgorithmName = "MD5";
        Object credentials = "123456";
        Object salt = ByteSource.Util.bytes("admin");
        int hashIterations = 1024;

        Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
        System.out.println(result);
    }

    /*授权的时候需要实现的方法*/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //1. 从 PrincipalCollection 中来获取登录用户的信息
        Object principal = principals.getPrimaryPrincipal();

        //2. 利用登录的用户的信息来用户当前用户的角色或权限(可能需要查询数据库)
        Set<String> roles = new HashSet<>();
        if("user".equals(principal)){
            roles.add("user");
        }
        if("admin".equals(principal)){
            roles.add("admin");
        }
        //3. 创建 SimpleAuthorizationInfo, 并设置其 reles 属性.
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);

        //4. 返回 SimpleAuthorizationInfo 对象.
        return info;
    }
}
