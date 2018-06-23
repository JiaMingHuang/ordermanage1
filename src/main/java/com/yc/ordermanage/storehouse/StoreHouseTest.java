package com.yc.ordermanage.storehouse;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.yc.ordermanage.storehouse.domain.StoreHouseVO;
import com.yc.ordermanage.storehouse.service.StoreHouseService;

public class StoreHouseTest {

	private StoreHouseService storeHouseService;
	
	@Test
	public void test() {
		List<StoreHouseVO> storeHouseVOs = storeHouseService.findStoreHouseByCondition("222");
		if(null != storeHouseVOs) {
			System.out.println(storeHouseVOs);
		}else {
			System.out.println("1");
		}
		
	}

}
