package com.yc.ordermanage.user.controller;

import com.yc.ordermanage.user.domain.UserVO;
import com.yc.ordermanage.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/main")
	public String initUserPage() {
		return "/user/user-main";
	}

	@GetMapping("/add")
	public String intiUserAdd() {
		return "/user/user-add";
	}

	@GetMapping("/user/{id}")
	public void getUser() {
	}

	@PutMapping("/user")
	public void putUser() {

	}

	@DeleteMapping("/user/{id}")
	public void deleteUser() {
	}

	@PostMapping("/user")
	public void postUser() {

	}

	@PostMapping("/query")
	@ResponseBody
	public List<UserVO> queryUser(UserVO user) {
		if (null == user.getUserid() || "".equals(user.getUserid())) {
			return userService.findAll();
		}
		return userService.findByUserid(user.getUserid());
	}

}
