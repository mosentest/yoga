package com.yoga.dto;

import java.io.Serializable;

import com.yoga.entity.TbUser;

public class UserDTO implements Serializable {
	private String username;
	private String password;

	public TbUser toObject() {
		TbUser tbUser = new TbUser();
		tbUser.setUserUsername(username);
		tbUser.setUserPassword(password);
		return tbUser;
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
