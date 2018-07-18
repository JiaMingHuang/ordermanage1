package com.yc.ordermanage.order.controller;

import java.io.InputStream;
import java.util.*;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.yc.ordermanage.order.domain.OrderModel;
import com.yc.ordermanage.order.domain.OrderVO;
import com.yc.ordermanage.order.service.OrderService;
import com.yc.ordermanage.orderdetail.domain.OrderDetailVO;
import com.yc.ordermanage.orderdetail.service.OrderDetailService;
import com.yc.ordermanage.user.domain.UserVO;
import com.yc.ordermanage.user.service.UserService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

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
		return orderService.deleteById(id);
	}
	
	/**
	* @Title: putUser 
	* @Description: 保存订单和订单商品
	* @return Boolean
	* @author kaming.Van.hwang
	* @date 2018年6月28日上午1:39:34
	 */
	@RequestMapping("/form")
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
	}
	
	@GetMapping("/alter/{id}")
	public String initOrderAlter(Model model, @PathVariable Long id) {
		model.addAttribute("order", orderService.findById(id).get());
		model.addAttribute("orderdetails", orderDetailService.findListById(id));
		return "order/order-alert";
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
	@RequestMapping("/batchImport")
	@ResponseBody
	public Map<String,String> batchImport(MultipartFile file) throws Exception{
		String result = "";
		Map<String,String> map = new HashMap<>();
		List<OrderDetailVO> orderDetailVOList = new ArrayList<>();
		try {
			//校验文件格式
			if (!file.getOriginalFilename().endsWith(".xls") && !file.getOriginalFilename().endsWith(".xlsx")) {
				result = "请上传.xls或.xlsx格式文件";
				map.put("failure",result);
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
					OrderDetailVO orderDetailVO = checkExcel(row,index);
					if(StringUtils.isEmpty(orderDetailVO.getRemark01())){//remark01为辨识是否正确数据的flag，若为空，则该行数据正确，若不为空，则该行数据有误
						orderDetailVOList.add(orderDetailVO);
					}else{
						result = orderDetailVO.getRemark01();
						map.put("failure",result);
						return map;
					}
				}
				if (flag == false && index == 1) {
					result = "数据不能为空或导入的列表第一行为空，请更正后重新导入";
					map.put("failure",result);
					return map;
				}
			}
		} catch (InvalidFormatException e) {
			result = "导入步骤一校验数据出现异常：" + e.getMessage();
			map.put("failure",result);
			return map;
		}
		//步骤二，开始将excel数据导入数据库
		if(null != orderDetailVOList && orderDetailVOList.size() > 0){
			//分批插入数据库
			int idx = 0;
			for (int i=0; i<orderDetailVOList.size(); i++){
				if(i % 50 == 0 && i != 0){
					List<OrderDetailVO> temp = orderDetailVOList.subList(idx,i);

				}
			}
		}
		return null;
	}

	/**
	 * @param row
	 * @param index
	 * @return
	 */
	private OrderDetailVO checkExcel(Row row, int index) {
		if(null != row){
			OrderDetailVO orderDetailVO = new OrderDetailVO();
			for(RowColum rColum : RowColum.values()){
				Cell cell = row.getCell(rColum.getIndex());
				if(null != cell){
					switch (rColum){
						case 型号品名颜色:{
							orderDetailVO.setName_spec_color(cell.getStringCellValue());
							break;
						}
						case 件数:{
							orderDetailVO.setAmount((int)cell.getNumericCellValue());
							break;
						}
						case 装箱数:{
							orderDetailVO.setPackagenumber((int)cell.getNumericCellValue());
							break;
						}
						case 总数量:{
							orderDetailVO.setTotalnumber((int)cell.getNumericCellValue());
							break;
						}
						case 单位:{
							orderDetailVO.setUnit(cell.getStringCellValue());
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
							/*String factory = cell.getStringCellValue();
							if("金奇".equals(factory)){
								orderDetailVO.setFactoryid();
							}*/
							break;
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
					}else if(rColum == RowColum.厂家){
						orderDetailVO.setRemark01("第" + index + "行的厂家为空，请填写完整后重新上传");
					}
				}
			}
			return orderDetailVO;
		}else{
			return null;
		}
	}
}
