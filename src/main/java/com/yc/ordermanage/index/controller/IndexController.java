package com.yc.ordermanage.index.controller;

import com.yc.ordermanage.common.util.DESUtil;
import com.yc.ordermanage.order.service.OrderService;
import com.yc.ordermanage.user.domain.UserVO;
import com.yc.ordermanage.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

	@Autowired
	private UserService userService;

	@Autowired
	private OrderService orderService;

	@GetMapping("/index-page")
	public String index(Model model) {
		// 获取三个月内未下单客户
		model.addAttribute("clientnameList",orderService.findNoOrderClientname());
		return "index";
	}

	@GetMapping("/login-page")
	public String login() {
		return "login";
	}

	@GetMapping("/main-page")
	public String main() {
		return "main";
	}

	@GetMapping("/loginerror-page")
	public String loginerror() {
		return "loginerror";
	}

	/**
	 * 登入
	 * @param model
	 * @param userid
	 * @param password
	 * @return
	 */
	@PostMapping("/login")
	public String login(Model model, String userid, String password) {
		List<UserVO> userList = userService.findByUserid(userid);
		if (userList.isEmpty()) {
			model.addAttribute("errormessage", "用户不存在");
			return "login";
		}
		if (!DESUtil.encryptBasedDes(password).equals(userList.get(0).getPassword())) {
			model.addAttribute("errormessage", "密码错误");
			return "login";
		}
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		request.getSession().setAttribute("currentUser", userList.get(0));
		return "redirect:/index-page";
	}

	/**
	 * 登出
	 * @return
	 */
	@GetMapping("/logout")
	public String logout(){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		request.getSession().removeAttribute("currentUser");
		return "redirect:/login-page";
	}
}
