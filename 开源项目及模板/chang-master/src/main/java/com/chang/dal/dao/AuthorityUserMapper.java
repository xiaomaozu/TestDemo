package com.chang.dal.dao;

import com.chang.dal.model.AuthorityUser;
import com.chang.dal.model.AuthorityUserExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AuthorityUserMapper {
    long countByExample(AuthorityUserExample example);

    int deleteByExample(AuthorityUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AuthorityUser record);

    int insertSelective(AuthorityUser record);

    List<AuthorityUser> selectByExample(AuthorityUserExample example);

    AuthorityUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AuthorityUser record, @Param("example") AuthorityUserExample example);

    int updateByExample(@Param("record") AuthorityUser record, @Param("example") AuthorityUserExample example);

    int updateByPrimaryKeySelective(AuthorityUser record);

    int updateByPrimaryKey(AuthorityUser record);

    @Select(value = "select * from authority_user where user_name=#{userName}")
    AuthorityUser selectByUserName(String userName);
}