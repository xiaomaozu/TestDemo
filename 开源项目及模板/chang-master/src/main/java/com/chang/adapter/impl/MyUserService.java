package com.chang.adapter.impl;

import com.chang.dal.dao.AuthorityUserMapper;
import com.chang.dal.model.AuthorityUser;
import com.chang.dal.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author 常培兵
 * @Description: spring security 用户查询
 * @date 2018/4/19 11:03
 */
@Service
public class MyUserService implements UserDetailsService {

    @Autowired
    private AuthorityUserMapper authorityUserMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        AuthorityUser user=authorityUserMapper.selectByUserName(s);
        if (user==null){
            throw new UsernameNotFoundException("该用户不存在！");
        }
        UserEntity userEntity = new UserEntity(user.getUserName(),user.getPassWord(),user.getEnabled(),user.getAccountNonExpired(),user.getAccountNonLocked(),user.getCredentialsNonExpired());
        return userEntity;
    }
}
