package com.yc.ordermanage.storehouse.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "STOREHOUSE")
public class StoreHouseVO{

	@Id
	@GeneratedValue
	private Long id;
	@Column
	private String name_spec_color;
	@Column
	private int amount;
	@Column
	private Date createDate;
	@Column
	private Date updateDate;
	@Column
	private String delFlag;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	
}
