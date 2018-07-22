package com.yc.ordermanage.order.controller;

import com.yc.ordermanage.common.util.ChangeToPinYin;
import com.yc.ordermanage.order.domain.OrderModel;
import com.yc.ordermanage.order.domain.OrderVO;
import com.yc.ordermanage.order.service.OrderService;
import com.yc.ordermanage.orderdetail.domain.OrderDetailVO;
import com.yc.ordermanage.orderdetail.service.OrderDetailService;
import com.yc.ordermanage.user.domain.UserVO;
import com.yc.ordermanage.user.service.UserService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.*;

@RequestMapping("/order")
@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderDetailService orderDetailService;
	@Autowired
	private UserService userService;
	
	@RequestMapping("/initOrderPage")
	public String initOrderPage() {
		return "order/order-table";
	}

	@GetMapping("/add")
	public String orderForm(Model model){

		/*Model
		List<UserVO> factory = userService.getFactory(3);
		session.setAttribute("factory", factory);*/
		/*UserVO userVO = new UserVO();
		userVO.setAccounttype(3);*/
		List<UserVO> factory = userService.getFactory(3);
		model.addAttribute("factory", factory);
		return "order/order-form";
	}
	
	@RequestMapping("/findAll")
	@ResponseBody
	public List<OrderVO> findAll(){
		List<OrderVO> orderVOs = orderService.findAll();
		return orderVOs;
	}
	
	@DeleteMapping("/deleteById/{id}")
	@ResponseBody
	public Boolean deleteById(@PathVariable Long id) {
		List<OrderDetailVO> orderDetailVOList = orderDetailService.findListById(id);
		if (orderDetailVOList.size() != 0) {
			orderDetailService.batchDelete(orderDetailVOList);
		}
		return orderService.deleteById(id);
	}
	
	/**
	* @Title: putUser 
	* @Description: 保存订单和订单商品
	* @return Boolean
	* @author kaming.Van.hwang
	* @date 2018年6月28日上午1:39:34
	 */
	/*@RequestMapping("/form")
	@ResponseBody
	public Boolean putUser(@RequestBody OrderModel orderModel) {
		OrderVO orderVO = orderService.addOrder(orderModel.getOrderVO());
		List<OrderDetailVO> orderDetailVOList = orderModel.getOrderDetailVOList();
		for (OrderDetailVO orderDetailVO : orderDetailVOList) {
			orderDetailVO.setOrderid(orderVO.getId());
			orderDetailVO.setOrdernumber(orderVO.getOrdernumber());
			orderDetailVO.setDelflag("0");
			orderDetailService.addOrderDetail(orderDetailVO);
		}
		return true;
	}*/

	/**
	 * @param orderModel
	 * @return
	 */
	@RequestMapping("/form")
	@ResponseBody
	public Map<String, Object> postOrderMainInfo(@RequestBody OrderModel orderModel) {
		OrderVO orderVO = orderService.addOrder(orderModel.getOrderVO());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("FLAG", true);
		map.put("ORDERID", orderVO.getId());
		return map;
	}


	@GetMapping("/alter/{id}&{index}")
	public String initOrderAlter(Model model, @PathVariable Long id, @PathVariable int index) {
		model.addAttribute("order", orderService.findById(id).get());
		model.addAttribute("orderdetails", orderDetailService.findListById(id));
		if (1 == index) {
			return "order/order-alert";
		} else if (2 == index) {
			return "order/order-factory-not-delivery";
		} else {
			return "order/order-storehouse-actual-take";
		}
	}

	/**
	 * 确认收货
	 * @param id
	 * @return
	 */
	@RequestMapping("/doTakeOver/{id}")
	@ResponseBody
	public String doTakeOver(@PathVariable Long id){
		OrderVO orderVO = orderService.findById(id).get();
		orderVO.setIstakeover("1");
		orderService.updateOrderVO(new Date(), id);
		return "SUCCESS";
	}

	/**
	 * 确认收款
	 * @param id
	 * @return
	 */
	@RequestMapping("/doGather/{id}")
	@ResponseBody
	public String doGather(@PathVariable Long id){
		OrderVO orderVO = orderService.findById(id).get();
		orderVO.setIsgather("1");//如果确认收款则为1，未收款为0

		//订单完成时订单下的商品列表也完成 暂射delFlag为1 即订单已完成\
		List<OrderDetailVO> orderDetails = orderDetailService.findListById(orderVO.getId());
		for (OrderDetailVO orderDetail : orderDetails) {
			orderDetailService.update(orderDetail);
		}
		orderService.updateOrderVO(new Date(), id);
		return "SUCCESS";
	}

	/**
	 * 修改订单
	 * @param orderModel
	 * @return
	 */
	@PostMapping("/updateOrder")
	@ResponseBody
	public Boolean updateOrder(@RequestBody OrderModel orderModel){
		OrderVO orderVO = orderModel.getOrderVO();
		List<OrderDetailVO> orderDetailVOList = orderModel.getOrderDetailVOList();
		orderService.updateOrderVO(new Date(), orderVO.getId());//更新主订单修改时间
		for (OrderDetailVO orderDetailVO : orderDetailVOList) {//更新订单中的商品列表
			orderDetailService.updateOrderDetail(orderDetailVO);
		}
		return true;
	}

	/**
	 * 查看未收款订单，即未完成订单
	 * @param model
	 * @return
	 */
	@RequestMapping("/findUnfinishedOrder")
	@ResponseBody
	public List<OrderVO> findUnfinishedOrder(Model model){
		List<OrderVO> orderVOS = orderService.findUnfinishedOrder("0");//搜索未收款的所有订单
		return orderVOS;
	}

	/**
	 * 查看所有已完成订单，即已收款订单
	 */
	@RequestMapping("/findFinishedOrder")
	@ResponseBody
	public List<OrderVO> findFinishedOrder(Model model){
		List<OrderVO> orderVOS = orderService.findFinishedOrder("1");//搜索已收款的所有订单
		return orderVOS;
	}

	/**
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/batchImport/{orderId}")
	@ResponseBody
	public Map<String, Object> batchImport(MultipartFile file, @PathVariable Long orderId) throws Exception{
		String result = "";
		Map<String, Object> map = new HashMap<>();
		List<OrderDetailVO> orderDetailVOList = new ArrayList<>();
		try {
			//校验文件格式
			if (!file.getOriginalFilename().endsWith(".xls") && !file.getOriginalFilename().endsWith(".xlsx")) {
				orderService.deleteById(orderId);//导入的excel文件有误则删除原保存的订单主信息
				result = "请上传.xls或.xlsx格式文件";
				map.put("flag", false);
				map.put("msg",result);
				return map;
			}
			InputStream inputStream = file.getInputStream();
			//获取上传的excel表格
			Workbook workbook = WorkbookFactory.create(inputStream);
			//获取指定的sheet
			Sheet sheet1 = workbook.getSheetAt(0);
			/*//获取表格总行数
			int rowNum = sheet1.getLastRowNum();
			//获取总列数
			int colNum = sheet1.getRow(0).getLastCellNum();*/
			inputStream.close();
			workbook.close();
			if(sheet1 != null){
				// 遍历excel,从第2行开始 即 index=1,逐个获取单元格的内容,然后进行格式处理
				Row row = null;
				int index = 1;
				boolean flag = true;
				while (flag){
					row = sheet1.getRow(index);
					if(row == null){
						flag = false;
						continue;
					}
					index++;
					OrderDetailVO orderDetailVO = checkExcel(row, index, orderId);
					if (StringUtils.isEmpty(orderDetailVO.getRemark01())) {
						//remark01为辨识是否正确数据的flag，若为空，则该行数据正确，若不为空，则该行数据有误
						orderDetailVO.setOrderid(orderId);
						orderDetailVOList.add(orderDetailVO);
					} else {
						orderService.deleteById(orderId);//导入的excel文件有误则删除原保存的订单主信息
						result = orderDetailVO.getRemark01();
						map.put("flag", false);
						map.put("msg",result);
						return map;
					}
				}
				if (flag == false && index == 1) {
					orderService.deleteById(orderId);//导入的excel文件有误则删除原保存的订单主信息
					result = "数据不能为空或导入的列表第一行为空，请更正后重新导入";
					map.put("flag", false);
					map.put("msg",result);
					return map;
				}
			}
		} catch (InvalidFormatException e) {
			orderService.deleteById(orderId);//导入的excel文件有误则删除原保存的订单主信息
			result = "导入步骤一校验数据出现异常：" + e.getMessage();
			map.put("flag", false);
			map.put("msg",result);
			return map;
		}
		//步骤二，开始将excel数据导入数据库
		if(null != orderDetailVOList && orderDetailVOList.size() > 0){
			orderDetailService.batchInsert(orderDetailVOList);
		}
		result = "导入成功";
		map.put("flag", true);
		map.put("msg",result);
		return map;
	}

	/**
	 * @param row
	 * @param index
	 * @return
	 */
	private OrderDetailVO checkExcel(Row row, int index, Long orderId) {
		OrderVO orderVO = orderService.findOneById(orderId);
		List<UserVO> factoryUsers = null;
		if ("1".equals(orderVO.getOrdertype())) {
			factoryUsers = userService.getFactory(3);
		} else if ("2".equals(orderVO.getOrdertype())) {
			factoryUsers = userService.getFactory(4);
		}

		if (null != row) {
			OrderDetailVO orderDetailVO = new OrderDetailVO();
			for(RowColum rColum : RowColum.values()){
				Cell cell = row.getCell(rColum.getIndex());
				if (null != cell) {
					switch (rColum){
						case 型号品名颜色:{
							orderDetailVO.setName_spec_color(cell.getStringCellValue().trim());
							break;
						}
						case 件数:{
							orderDetailVO.setAmount((int) cell.getNumericCellValue());
							break;
						}
						case 装箱数:{
							orderDetailVO.setPackagenumber((int)cell.getNumericCellValue());
							break;
						}
						case 总数量: {
							orderDetailVO.setTotalnumber((int) cell.getNumericCellValue());
							break;
						}
						case 单位:{
							orderDetailVO.setUnit(cell.getStringCellValue().trim());
							break;
						}
						case 单价:{
							orderDetailVO.setPrice(cell.getNumericCellValue());
							break;
						}
						case 总金额:{
							orderDetailVO.setTotal(cell.getNumericCellValue());
							break;
						}
						case 厂家:{
							if (!StringUtils.isEmpty(cell.getStringCellValue().trim())) {
								String factoryName = cell.getStringCellValue().trim();
								for (UserVO fcyUser : factoryUsers) {
									if (fcyUser.getChinesename().equals(factoryName)) {
										orderDetailVO.setFactoryid(fcyUser.getId());
										orderDetailVO.setFactorychinesename(fcyUser.getChinesename());
										break;
									}
								}
							}
							break;
						}
						case 自填厂家: {
							boolean flag = true;
							//先检查与数据库是否有匹配，有匹配的则使用原有的厂家
							//若不存在则新增厂家，新增一个工厂用户并且初始密码为123
							if (null == orderDetailVO.getFactoryid()) {
								String newFcyName = cell.getStringCellValue().trim();
								//先校验user表中是否存在该用户
								for (UserVO user : factoryUsers) {
									if (newFcyName.equals(user.getChinesename())) {
										//已存在相同值，则取该值id即可
										flag = false;
										orderDetailVO.setFactoryid(user.getId());
										orderDetailVO.setFactorychinesename(user.getChinesename());
										break;
									}
								}
								if (flag == true) {
									String pinyinName = ChangeToPinYin.getPingYin(newFcyName);
									UserVO userVO = new UserVO();
									userVO.setUserid(pinyinName);
									userVO.setPassword("123");
									userVO.setChinesename(newFcyName);
									if ("1".equals(orderVO.getOrdertype())) {
										userVO.setAccounttype(3);
									} else if ("2".equals(orderVO.getOrdertype())) {
										userVO.setAccounttype(4);
									}
									UserVO newFcyUser = userService.createUser(userVO);
									orderDetailVO.setFactoryid(newFcyUser.getId());
									orderDetailVO.setFactorychinesename(newFcyName);
									break;
								} else {
									break;
								}

							}
						}
						default:
							break;
					}
				}else{
					if(rColum == RowColum.型号品名颜色){
						orderDetailVO.setRemark01("第" + index + "行的型号品名颜色为空，请填写完整后重新上传");
					}else if(rColum == RowColum.件数){
						orderDetailVO.setRemark01("第" + index + "行的件数为空，请填写完整后重新上传");
					}else if(rColum == RowColum.装箱数){
						orderDetailVO.setRemark01("第" + index + "行的装箱数为空，请填写完整后重新上传");
					}else if(rColum == RowColum.总数量){
						orderDetailVO.setRemark01("第" + index + "行的总数量为空，请填写完整后重新上传");
					}else if(rColum == RowColum.单位){
						orderDetailVO.setRemark01("第" + index + "行的单价为空，请填写完整后重新上传");
					}else if(rColum == RowColum.单价){
						orderDetailVO.setRemark01("第" + index + "行的型号品名颜色为空，请填写完整后重新上传");
					}else if(rColum == RowColum.总金额){
						orderDetailVO.setRemark01("第" + index + "行的总金额为空，请填写完整后重新上传");
					} else if (rColum == RowColum.自填厂家 && null == orderDetailVO.getFactoryid()) {
						orderDetailVO.setRemark01("第" + index + "行的厂家都为空，请填写完整后重新上传");
					}
				}
			}
			orderDetailVO.setCreatedate(new Date());
			orderDetailVO.setUpdatedate(new Date());
			return orderDetailVO;
		}else{
			return null;
		}
	}
}
