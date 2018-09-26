package com.chang.facade.service;

import com.chang.facade.dto.request.AuthorityUserRequestDTO;
import com.chang.facade.dto.response.AuthorityUserResponseDTO;

import java.util.List;

/**
 * @author 常培兵
 * @Description: 描述
 * @date 2018/3/22 11:14
 */
public interface AuthorityUserService {

    /**
     * 注册用户
     * @param requestDTO
     * @throws Exception
     */
    void insertSelective(AuthorityUserRequestDTO requestDTO)throws Exception;

    /**
     * 查询所有用户
     * @return
     * @throws Exception
     */
    List<AuthorityUserResponseDTO> selectAll() throws Exception;

    /**
     * 根据用户名查询用户信息
     * @param userName
     * @return
     */
    AuthorityUserResponseDTO selectByName(String userName);
}
