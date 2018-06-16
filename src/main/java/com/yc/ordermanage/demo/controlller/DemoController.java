package com.yc.ordermanage.demo.controlller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DemoController {

	@RequestMapping("/hello")
	public String index() {
		return "hello";
	}

}
