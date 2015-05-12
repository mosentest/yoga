package com.yoga.entity;

/**
 * TbRoleLimit entity
 */

public class TbRoleLimit implements java.io.Serializable {

	// Fields

	private Integer id;
	private TbRole tbRole;
	private TbLimit tbLimit;

	// Constructors

	/** default constructor */
	public TbRoleLimit() {
	}

	/** full constructor */
	public TbRoleLimit(TbRole tbRole, TbLimit tbLimit) {
		this.tbRole = tbRole;
		this.tbLimit = tbLimit;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TbRole getTbRole() {
		return this.tbRole;
	}

	public void setTbRole(TbRole tbRole) {
		this.tbRole = tbRole;
	}

	public TbLimit getTbLimit() {
		return this.tbLimit;
	}

	public void setTbLimit(TbLimit tbLimit) {
		this.tbLimit = tbLimit;
	}

}