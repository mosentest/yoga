package com.yoga.entity;


/**
 * TbUser entity
 */

public class TbUser implements java.io.Serializable {

	// Fields

	private String userId;
	private TbStaff tbStaff;
	private String userUsername;
	private String userPassword;
//	private Set tbUserRoles = new HashSet(0);

	// Constructors

	/** default constructor */
	public TbUser() {
	}

	/** full constructor */
	public TbUser(TbStaff tbStaff, String userUsername, String userPassword/*,
			Set tbUserRoles*/) {
		this.tbStaff = tbStaff;
		this.userUsername = userUsername;
		this.userPassword = userPassword;
	/*	this.tbUserRoles = tbUserRoles;*/
	}

	// Property accessors

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public TbStaff getTbStaff() {
		return this.tbStaff;
	}

	public void setTbStaff(TbStaff tbStaff) {
		this.tbStaff = tbStaff;
	}

	public String getUserUsername() {
		return this.userUsername;
	}

	public void setUserUsername(String userUsername) {
		this.userUsername = userUsername;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

/*	public Set getTbUserRoles() {
		return this.tbUserRoles;
	}

	public void setTbUserRoles(Set tbUserRoles) {
		this.tbUserRoles = tbUserRoles;
	}*/

}