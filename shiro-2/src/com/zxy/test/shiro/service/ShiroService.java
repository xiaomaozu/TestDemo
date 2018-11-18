package com.zxy.test.shiro.service;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;

import java.util.Date;

public class ShiroService {

    @RequiresRoles({"admin"})
    public void testMethod(){
        Session session = SecurityUtils.getSubject().getSession();
        System.out.println("test method "+ new Date());
        System.out.println(session.getAttribute("key"));
    }

}
