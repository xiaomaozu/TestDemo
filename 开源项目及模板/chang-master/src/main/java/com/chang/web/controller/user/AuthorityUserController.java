package com.chang.web.controller.user;

import com.chang.facade.dto.ResponseDTOWrapper;
import com.chang.facade.dto.request.AuthorityUserRequestDTO;
import com.chang.facade.service.AuthorityUserService;
import com.chang.web.controller.base.BaseController;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * @author 常培兵
 * @Description: User控制器
 * @date 2018/3/22 11:06
 */
@RestController
@RequestMapping("user")
public class AuthorityUserController extends BaseController{

    @Autowired
    private AuthorityUserService authorityUserService;

    @ApiOperation(value = "查询所有用户信息", response = ResponseDTOWrapper.class, notes = "查询所有用户信息")
    @GetMapping
    public ResponseDTOWrapper search() throws Exception {
        return createResponse(authorityUserService.selectAll());
    }
    @ApiOperation(value = "添加用户信息", response = ResponseDTOWrapper.class, notes = "添加用户信息")
    @PostMapping
    public ResponseDTOWrapper addUser(@RequestBody @Valid AuthorityUserRequestDTO requestDTO) throws Exception {
        authorityUserService.insertSelective(requestDTO);
        return  createResponse();
    }

    @ApiOperation(value = "查询用户信息", response = ResponseDTOWrapper.class, notes = "按照用户名查询所有用户信息")
    @GetMapping("{name}")
    public ResponseDTOWrapper findByName(@PathVariable String name) throws Exception{
        return createResponse(authorityUserService.selectByName(name));
    }

}
