package com.yc.ordermanage.user.controller;

import com.yc.ordermanage.user.domain.UserVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/user")
public class UserController {

	@RequestMapping("/page")
	public String initUserPage() {
		return "/user/user";
	}

	@RequestMapping("/add")
	public String intiUserAdd(){
		return "/user/userAdd";
	}

	@RequestMapping("/query")
	@ResponseBody
	public UserVO queryUser(String username){
		UserVO userVO = new UserVO();
		userVO.setUserid("sysadmin");
		return userVO;
	}

}
