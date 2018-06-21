package com.yc.ordermanage.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class UserController {

	@RequestMapping("/page")
	public String initUserPage() {
		return "/user/user";
	}

}
