package com.yc.ordermanage.orderdetail.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ORDERDETAIL")
public class OrderDetailVO {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	@Column
	private Long orderid;
	@Column
	private Long factoryid;
	@Column
	private String name_spec_color;
	@Column
	private int amount;//件数
	@Column
	private double price;
	@Column
	private double total;//总金额
	@Column
	private String unit;
	@Column
	private int actual_take_amount;//收货件数
	@Column
	private Date createdate;
	@Column
	private Date updatedate;
	@Column
	private String delflag;//0未完成 1已完成
	@Column
	private String ordernumber;//订单编号
	@Column
	private String remark01;//备注
	@Column
	private int packagenumber;//装箱数
	@Column
	private int totalnumber;//总数量=件数*装箱数
	@Column
	private int actual_take_total;//收货数量=收货件数*装箱数
	@Column
	private int factory_not_delivery_amount;//工厂未交件数=件数-收货件数
	@Column
	private int factory_not_delivery_totalnumber;//工厂未交总数量=工厂未交件数*装箱数
	@Column
	private double factory_not_delivery_total;//工厂未交货总金额=工厂未交总数量*单价
	@Column
	private int storehouse_actual_take_amount;//仓库实收件数=收货件数
	@Column
	private int storehouse_actual_take_totalnumber;//仓库实收总数量=仓库实收件数*装箱数
	@Column
	private double storehouse_actual_take_total;//仓库实收总金额=仓库实收总数量*单价

	public int getActual_take_total() {
		return actual_take_total;
	}

	public void setActual_take_total(int actual_take_amount) {//收货数量=收货件数*装箱数
		this.actual_take_total = actual_take_amount * this.packagenumber;
	}

	public int getFactory_not_delivery_amount() {
		return factory_not_delivery_amount;
	}

	public void setFactory_not_delivery_amount(int actual_take_amount) {//工厂未交件数=件数-收货件数
		this.factory_not_delivery_amount = this.amount - actual_take_amount;
	}

	public int getFactory_not_delivery_totalnumber() {
		return factory_not_delivery_totalnumber;
	}

	public void setFactory_not_delivery_totalnumber(int actual_take_amount) {//工厂未交总数量=工厂未交件数*装箱数
		this.factory_not_delivery_totalnumber = (this.amount - actual_take_amount) * this.packagenumber;
	}

	public double getFactory_not_delivery_total() {
		return factory_not_delivery_total;
	}

	public void setFactory_not_delivery_total(int actual_take_amount) {//工厂未交货总金额=工厂未交总数量*单价
		this.factory_not_delivery_total = (this.amount - actual_take_amount) * this.packagenumber * this.price;
	}

	public int getStorehouse_actual_take_amount() {
		return storehouse_actual_take_amount;
	}

	public void setStorehouse_actual_take_amount(int actual_take_amount) {//仓库实收件数=收货件数
		this.storehouse_actual_take_amount = actual_take_amount;
	}

	public int getStorehouse_actual_take_totalnumber() {
		return storehouse_actual_take_totalnumber;
	}

	public void setStorehouse_actual_take_totalnumber(int actual_take_amount) {//仓库实收总数量=仓库实收件数*装箱数
		this.storehouse_actual_take_totalnumber = actual_take_amount * this.packagenumber;
	}

	public double getStorehouse_actual_take_total() {
		return storehouse_actual_take_total;
	}

	public void setStorehouse_actual_take_total(int actual_take_amount) {//仓库实收总金额=仓库实收总数量*单价
		this.storehouse_actual_take_total = actual_take_amount * this.packagenumber * this.price;
	}

	public int getTotalnumber() {
		return totalnumber;
	}

	public void setTotalnumber(int totalnumber) {//总数量=件数*装箱数
		this.totalnumber = totalnumber;
	}

	public int getPackagenumber() {
		return packagenumber;
	}

	public void setPackagenumber(int packagenumber) {
		this.packagenumber = packagenumber;
	}

	public String getRemark01() {
		return remark01;
	}

	public void setRemark01(String remark01) {
		this.remark01 = remark01;
	}

	public String getOrdernumber() {
		return ordernumber;
	}

	public void setOrdernumber(String ordernumber) {
		this.ordernumber = ordernumber;
	}

	public Long getOrderid() {
		return orderid;
	}

	public void setOrderid(Long orderid) {
		this.orderid = orderid;
	}

	public Long getFactoryid() {
		return factoryid;
	}

	public void setFactoryid(Long factoryid) {
		this.factoryid = factoryid;
	}

	public String getName_spec_color() {
		return name_spec_color;
	}

	public void setName_spec_color(String name_spec_color) {
		this.name_spec_color = name_spec_color;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public int getActual_take_amount() {
		return actual_take_amount;
	}

	public void setActual_take_amount(int actual_take_amount) {
		this.actual_take_amount = actual_take_amount;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public Date getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}

	public String getDelflag() {
		return delflag;
	}

	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
