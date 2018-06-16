package com.yc.ordermanage.demo.controlller;

import com.yc.ordermanage.demo.dao.DemoRepository;
import com.yc.ordermanage.demo.domain.UserVO;
import com.yc.ordermanage.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/demo")
public class DemoRestController {

	@Autowired
	private DemoService demoService;
	@Autowired
	private DemoRepository demoRepository;

	@RequestMapping("findAll")
	@ResponseBody
	public List<UserVO> findAll() {
		return demoService.findAll();
	}

	@RequestMapping("findOne")
	@ResponseBody
	public UserVO findOne(String userid) {
		return demoService.findOne(userid);
	}

}
