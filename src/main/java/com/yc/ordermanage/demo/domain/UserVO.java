package com.yc.ordermanage.demo.domain;

import com.yc.ordermanage.base.domain.BaseVO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "T_USER")
public class UserVO /*extends BaseVO*/{

//	private static final long serialVersionUID = -1L;

	@Id
	private Long userid;
	@Column
	private String username;
	@Column
	private String remark;
	@Column
	private Date createtime;

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
}
