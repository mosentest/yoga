package com.yoga.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * TbLimit entity. @author MyEclipse Persistence Tools
 */

public class TbLimit implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String href;
//	private Set tbRoleLimits = new HashSet(0);

	// Constructors

	/** default constructor */
	public TbLimit() {
	}

	/** full constructor */
	public TbLimit(String name/*, Set tbRoleLimits*/) {
		this.name = name;
//		this.tbRoleLimits = tbRoleLimits;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public Set getTbRoleLimits() {
//		return this.tbRoleLimits;
//	}
//
//	public void setTbRoleLimits(Set tbRoleLimits) {
//		this.tbRoleLimits = tbRoleLimits;
//	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

}