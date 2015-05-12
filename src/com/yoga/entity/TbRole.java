package com.yoga.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * TbRole entity
 */

public class TbRole implements java.io.Serializable {

	// Fields

	private Integer id;
	private String type;
//	private Set tbRoleLimits = new HashSet(0);
//	private Set tbUserRoles = new HashSet(0);

	// Constructors

	/** default constructor */
	public TbRole() {
	}

	/** full constructor */
	public TbRole(String type/*, Set tbRoleLimits, Set tbUserRoles*/) {
		this.type = type;
//		this.tbRoleLimits = tbRoleLimits;
//		this.tbUserRoles = tbUserRoles;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}
//
//	public Set getTbRoleLimits() {
//		return this.tbRoleLimits;
//	}
//
//	public void setTbRoleLimits(Set tbRoleLimits) {
//		this.tbRoleLimits = tbRoleLimits;
//	}
//
//	public Set getTbUserRoles() {
//		return this.tbUserRoles;
//	}
//
//	public void setTbUserRoles(Set tbUserRoles) {
//		this.tbUserRoles = tbUserRoles;
//	}

}