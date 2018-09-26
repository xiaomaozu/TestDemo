package com.chang.adapter.impl;

import com.chang.dal.dao.AuthorityUserMapper;
import com.chang.dal.model.AuthorityUser;
import com.chang.dal.model.AuthorityUserExample;
import com.chang.facade.dto.request.AuthorityUserRequestDTO;
import com.chang.facade.dto.response.AuthorityUserResponseDTO;
import com.chang.facade.service.AuthorityUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 常培兵
 * @Description: 描述
 * @date 2018/4/19 13:40
 */
@Service
@Transactional(readOnly = true)
public class AuthorityUserServiceImpl implements AuthorityUserService {

    @Autowired
    private AuthorityUserMapper authorityUserMapper;

    @Override
    @Transactional(readOnly = false)
    public void insertSelective(AuthorityUserRequestDTO requestDTO) throws Exception {
        if (requestDTO==null){
            throw new Exception("新增用户对象不能为空！");
        }
        AuthorityUser user=new AuthorityUser();
        //复制对象，参数1：源数据，参数2：目标对象
        BeanUtils.copyProperties(requestDTO,user);
        //添加创建时间
        user.setCreatedAt(System.currentTimeMillis());
        //密码加密
        String password = requestDTO.getPassWord();
        password = new BCryptPasswordEncoder().encode(password);
        user.setPassWord(password);
        //保存用户对象
        authorityUserMapper.insertSelective(user);
    }

    @Override
    public List<AuthorityUserResponseDTO> selectAll() throws Exception {
        AuthorityUserExample example=new AuthorityUserExample();
        List<AuthorityUser> authorityUsers=authorityUserMapper.selectByExample(example);
        List<AuthorityUserResponseDTO> authorityUserResponseDTOS=new ArrayList<>();
        for (AuthorityUser user : authorityUsers){
            authorityUserResponseDTOS.add(new AuthorityUserResponseDTO(user.getId(),user.getUserName(),user.getPassWord(),user.getEnabled(),user.getCreatedAt()));
        }
        return authorityUserResponseDTOS;
    }

    @Override
    public AuthorityUserResponseDTO selectByName(String userName) {
        AuthorityUser user=authorityUserMapper.selectByUserName(userName);
        AuthorityUserResponseDTO responseDTO=new AuthorityUserResponseDTO();
        BeanUtils.copyProperties(user,responseDTO);
        return responseDTO;
    }
}
