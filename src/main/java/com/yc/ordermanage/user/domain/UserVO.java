package com.yc.ordermanage.user.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * USER 用户信息表
 */
@Entity
@Table(name = "T_USER")
public class UserVO {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	/**
	 * 用户名
	 */
	@Column
	private String userid;

	/**
	 * 密码
	 */
	@Column
	private String password;

	/**
	 * 用户类型：
	 * 1：店铺
	 * 2：仓库
	 * 3：厂家
	 * 4：电镀厂
	 */
	@Column
	private Integer accounttype;

	/**
	 * 联系电话
	 */
	@Column
	private Long contact;

	/**
	 * 负责人名称
	 */
	@Column
	private String managername;

	/**
	 * 公司名称
	 */
	@Column
	private String companyname;

	/**
	 * 地址
	 */
	@Column
	private String address;

	/**
	 * 创建时间
	 */
	@Column
	private Date createdate;

	/**
	 * 更新时间
	 */
	@Column
	private Date updatedate;

	/**
	 * 是否删除：
	 * 0：没删除
	 * 1：已删除
	 */
	@Column
	private Integer delflag;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAccounttype() {
		return accounttype;
	}

	public void setAccounttype(Integer accounttype) {
		this.accounttype = accounttype;
	}

	public Long getContact() {
		return contact;
	}

	public void setContact(Long contact) {
		this.contact = contact;
	}

	public String getManagername() {
		return managername;
	}

	public void setManagername(String managername) {
		this.managername = managername;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public Integer getDelflag() {
		return delflag;
	}

	public void setDelflag(Integer delflag) {
		this.delflag = delflag;
	}

	@Override
	public String toString() {
		return "UserVO{" +
				"id='" + id + '\'' +
				", userid='" + userid + '\'' +
				", password='" + password + '\'' +
				", accounttype=" + accounttype +
				", contact=" + contact +
				", managername='" + managername + '\'' +
				", companyname='" + companyname + '\'' +
				", address='" + address + '\'' +
				", createdate=" + createdate +
				", updatedate=" + updatedate +
				", delflag=" + delflag +
				'}';
	}
}
