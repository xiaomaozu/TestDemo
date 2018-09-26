package com.chang.web.controller.user;

import com.chang.web.controller.base.BaseController;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 常培兵
 * @Description: 描述
 * @date 2018/4/19 15:33
 */
@Controller
@RequestMapping("account")
public class LoginController extends BaseController {

    @RequestMapping(value="/login", method= RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value="/loginSuccessful", method= RequestMethod.GET)
    public String loginSuccessful(){
        return "redirect:http://localhost:8080/swagger-ui.html";
    }

    @RequestMapping(value="/error", method= RequestMethod.GET)
    public String error(){
        return "error";
    }

}
