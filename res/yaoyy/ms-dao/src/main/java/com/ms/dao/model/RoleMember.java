package com.ms.dao.model;

import java.io.Serializable;

public class RoleMember implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Integer roleId;
	
	private Integer memberId;

	private Role role;

	private Member member;
	
	public RoleMember(){}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
}