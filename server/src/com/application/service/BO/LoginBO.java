package com.application.service.BO;

import javax.xml.bind.annotation.XmlRootElement;

/*
 * Pojo class to allow serialization and deserialization of json objects
 */

@XmlRootElement
public class LoginBO {
	private String username;
	private String password;

	public LoginBO() {
	}

	public LoginBO(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
