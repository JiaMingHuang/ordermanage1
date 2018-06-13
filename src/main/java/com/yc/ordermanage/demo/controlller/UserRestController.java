package com.yc.ordermanage.demo.controlller;

import com.yc.ordermanage.demo.dao.UserRepository;
import com.yc.ordermanage.demo.domain.UserVO;
import com.yc.ordermanage.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserRestController {

	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;

	@RequestMapping("findAll")
	@ResponseBody
	public List<UserVO> findAll() {
		return userService.findAll();
	}

	@RequestMapping("findOne")
	@ResponseBody
	public UserVO findOne(String userid) {
		return userService.findOne(userid);
	}

}
