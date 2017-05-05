package com.pieces.dao.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 保存用户和代理商的绑定关系
 */
public class UserBind  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	//代理商id
	private Integer agentId;
	
	//终端用户id
	private Integer terminalId;
	
	private Date createTime;
	
	public UserBind(){}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getAgentId() {
		return agentId;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}
	
	public Integer getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(Integer terminalId) {
		this.terminalId = terminalId;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}