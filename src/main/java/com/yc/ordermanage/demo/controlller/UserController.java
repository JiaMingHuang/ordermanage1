package com.yc.ordermanage.demo.controlller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @RequestMapping("/demo")
    public String index() {
        return "/login/login";
    }

}
