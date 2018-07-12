package com.yc.ordermanage.user.controller;

import com.yc.ordermanage.user.domain.UserVO;
import com.yc.ordermanage.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/main")
	public String initUserPage() {
		return "user/user-main";
	}

	/**
	 * 初始化 新增页面
	 */
	@GetMapping("/add")
	public String initUserAdd() {
		return "user/user-add";
	}

	/**
	 * 初始化 修改页面
	 */
	@GetMapping("/alter/{id}")
	public String initUserAlter(Model model, @PathVariable Long id) {
		model.addAttribute("user", userService.findById(id).get());
		return "user/user-alter";
	}

	@GetMapping("/user/{id}")
	public void getUser() {
	}

	/**
	 * 新增用户
	 */
	@PutMapping("/user")
	@ResponseBody
	public Boolean putUser(UserVO userVO) {
		userService.createUser(userVO);
		return true;
	}

	/**
	 * 删除用户
	 */
	@DeleteMapping("/user/{id}")
	@ResponseBody
	public Boolean deleteUser(@PathVariable Long id) {
		return userService.deleteUserByid(id);
	}

	/**
	 * 修改用户
	 */
	@PostMapping("/user")
	@ResponseBody
	public Boolean postUser(UserVO userVO) {
		userService.updateUser(userVO);
		return true;
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
