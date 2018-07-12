package com.yc.ordermanage.storehouse.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
		return "storehouse/storehouse-table";
	}
	
	@RequestMapping("selectById/{id}")
	@ResponseBody
	public StoreHouseVO selectById(@PathVariable Long id) {
		return storeHouseService.selectById(id);
	}
	
	@DeleteMapping("deleteById/{id}")
	@ResponseBody
	public Boolean deleteById(@PathVariable Long id) {
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
	
	/**
	 * 初始化 新增页面
	 */
	@GetMapping("/add")
	public String initStoreHouseAdd() {
		return "storehouse/storehouse-form";
	}
	
	@RequestMapping("/storehouseSave")
	public String storehouseSave(StoreHouseVO storeHouseVO){
		storeHouseVO.setCreateDate(new Date());
		storeHouseService.save(storeHouseVO);
		return "storehouse/storehouse-table";
	}
	
	@GetMapping("/modifyStoreHouse/{id}")
	public String modifyStoreHouse(Model model, @PathVariable Long id) {
		model.addAttribute("storehouse", storeHouseService.findById(id).get());
		return "storehouse/storehouse-info";
	}
	
	@PostMapping("/update")
	@ResponseBody
	public Boolean updateStoreHouse(StoreHouseVO storeHouseVO) {
		storeHouseService.updateStoreHouse(storeHouseVO);
		return true;
	}
}
