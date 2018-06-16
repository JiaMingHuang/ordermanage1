package com.yc.ordermanage.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

	@RequestMapping("/user-page")
	public String index() {
		return "/user/user";
	}


}
