package com.ms.service.dto;

public class Password {

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	private String password;
	private String salt;
	private String plainPassword;
	public String getPlainPassword() {
		return plainPassword;
	}
	public void setPlainPassword(String plainPassword) {
		this.plainPassword = plainPassword;
	}
}
