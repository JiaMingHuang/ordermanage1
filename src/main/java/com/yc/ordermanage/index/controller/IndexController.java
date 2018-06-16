package com.yc.ordermanage.index.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("/index-page")
	public String index() {
		return "index";
	}

	@RequestMapping("/login-page")
	public String login() {
		return "login";
	}

	@RequestMapping("/main-page")
	public String main() {
		return "main";
	}
}
