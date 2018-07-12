package com.yc.ordermanage.orderdetail.domain;

import java.util.Date;

import javax.persistence.*;

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
	private int amount;
	@Column
	private double price;
	@Column
	private double total;
	@Column
	private String unit;
	@Column
	private int actual_take_amount;
	@Column
	private Date createdate;
	@Column
	private Date updatedate;
	@Column
	private String delflag;//0未完成 1已完成
	@Column
	private String ordernumber;

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
