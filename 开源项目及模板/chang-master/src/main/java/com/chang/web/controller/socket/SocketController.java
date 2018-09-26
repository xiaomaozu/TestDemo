package com.chang.web.controller.socket;

import com.chang.web.controller.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 常培兵
 * @Description: 描述
 * @date 2018/4/18 14:59
 */
@Controller
public class SocketController extends BaseController{

    @GetMapping("/socket")
    public String index() {
        return "index";
    }
}
