package com.yc.ordermanage.storehouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	
	@RequestMapping("/selectAll")
	@ResponseBody
	public List<StoreHouseVO> selectAll(Model model){
		List<StoreHouseVO> storeHouseVOs = storeHouseService.selectAll();
		return storeHouseVOs;
	}
	
	@RequestMapping("/initStoreHousePage")
	public String initStoreHousePage(Model model) {
		List<StoreHouseVO> storeHouseVOs = storeHouseService.selectAll();
		model.addAttribute("list",storeHouseVOs);
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
	
	@DeleteMapping("deleteById/{id}")
	@ResponseBody
	public Boolean deleteById(@PathVariable String id) {
		return storeHouseService.deleteById(id);
	}
	
	@RequestMapping("/findStoreHouseByCondition")
	@ResponseBody
	public List<StoreHouseVO> findStoreHouseByCondition(StoreHouseVO storeHouseVO) {
		if(StringUtils.isEmpty(storeHouseVO.getName_spec_color())) {
			return storeHouseService.selectAll();
		}
		return storeHouseService.findStoreHouseByCondition(storeHouseVO.getName_spec_color());
	}
}
