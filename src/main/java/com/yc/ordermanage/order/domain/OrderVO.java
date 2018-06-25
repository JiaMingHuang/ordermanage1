package com.yc.ordermanage.order.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_ORDER")
public class OrderVO {

	@Id
	@GeneratedValue
	private Long id;
	@Column
	private String ordernumber;
	@Column
	private String ordertype;
	@Column
	private String clientname;
	@Column
	private Date clientdeliverytime;
	@Column
	private Date factorydeliverytime;
	@Column
	private String contact;
	@Column
	private String address;
	@Column
	private String reason;
	@Column
	private Date createdate;
	@Column
	private Date updatedate;
	@Column
	private String delflag;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOrdernumber() {
		return ordernumber;
	}
	public void setOrdernumber(String ordernumber) {
		this.ordernumber = ordernumber;
	}
	public String getOrdertype() {
		return ordertype;
	}
	public void setOrdertype(String ordertype) {
		this.ordertype = ordertype;
	}
	public String getClientname() {
		return clientname;
	}
	public void setClientname(String clientname) {
		this.clientname = clientname;
	}
	public Date getClientdeliverytime() {
		return clientdeliverytime;
	}
	public void setClientdeliverytime(Date clientdeliverytime) {
		this.clientdeliverytime = clientdeliverytime;
	}
	public Date getFactorydeliverytime() {
		return factorydeliverytime;
	}
	public void setFactorydeliverytime(Date factorydeliverytime) {
		this.factorydeliverytime = factorydeliverytime;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
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
}
