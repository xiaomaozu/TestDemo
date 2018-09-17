package com.zxy.test.shiro.factory;

import java.util.LinkedHashMap;

public class FilterChainDefinitionMapBuilder {


    /**
     *  /login.jsp = anon
     *                 /shiro/login = anon
     *                 /shiro/logout = logout
     *
     *                 /** = user
     *                 &lt;!&ndash;角色&ndash;&gt;
     *                 /user.jsp = roles[user]
     *                 /admin.jsp = roles[admin]
     * @return
     */
    public LinkedHashMap<String,String> buildFilterChainDefinitionMap(){
        LinkedHashMap<String,String> map=new LinkedHashMap<>();
        map.put("/login.jsp", "anon");
        map.put("/shiro/login", "anon");
        map.put("/shiro/logout", "logout");
        map.put("/user.jsp", "roles[user]");
        map.put("/admin.jsp", "roles[admin]");
        map.put("/**", "user");
        return map;
    }
}
