package com.yc.ordermanage.storehouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yc.ordermanage.storehouse.domain.StoreHouseVO;
import com.yc.ordermanage.storehouse.service.StoreHouseService;

@Controller
@RequestMapping("/storehouse")
public class StoreHouseController {

	@Autowired
	private StoreHouseService storeHouseService;
	
	@RequestMapping("selectAll")
	public String selectAll(Model model){
		List<StoreHouseVO> storeHouseVOs = storeHouseService.selectAll();
		model.addAttribute("list", storeHouseVOs);
		return "/storehouse/storehouse-table";
	}
	
	@RequestMapping("skipToAdd")
	public String skipToAdd() {
		return "/storehouse/addGoods";
	}
	
	@RequestMapping("selectById/{id}")
	@ResponseBody
	public StoreHouseVO selectById(@PathVariable String id) {
		return storeHouseService.selectById(id);
	}
	
	@RequestMapping("deleteById/{id}")
	@ResponseBody
	public int deleteById(@PathVariable String id) {
		storeHouseService.deleteById(id);
		return 100;
	}
	
	@RequestMapping("findStoreHouseByCondition/{name_spec_color}")
	@ResponseBody
	public String findStoreHouseByCondition(@PathVariable String name_spec_color, Model model) {
		List<StoreHouseVO> shList = storeHouseService.findStoreHouseByCondition(name_spec_color);
		model.addAttribute("shList", shList);
		return "/storehouse/storehouse-table";
		
	}
}
