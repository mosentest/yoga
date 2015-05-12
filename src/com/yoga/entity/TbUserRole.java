package com.yoga.entity;

/**
 * TbUserRole entity
 */

public class TbUserRole implements java.io.Serializable {

	// Fields

	private Integer id;
	private TbUser tbUser;
	private TbRole tbRole;

	// Constructors

	/** default constructor */
	public TbUserRole() {
	}

	/** full constructor */
	public TbUserRole(TbUser tbUser, TbRole tbRole) {
		this.tbUser = tbUser;
		this.tbRole = tbRole;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TbUser getTbUser() {
		return this.tbUser;
	}

	public void setTbUser(TbUser tbUser) {
		this.tbUser = tbUser;
	}

	public TbRole getTbRole() {
		return this.tbRole;
	}

	public void setTbRole(TbRole tbRole) {
		this.tbRole = tbRole;
	}

}