package com.yoga.entity;

import java.util.HashSet;
import java.util.Set;
public class TbMemberType implements java.io.Serializable {

	// Fields

	private Integer id;
	private String type;
	private Set tbMembers = new HashSet(0);

	// Constructors

	/** default constructor */
	public TbMemberType() {
	}

	/** full constructor */
	public TbMemberType(String type, Set tbMembers) {
		this.type = type;
		this.tbMembers = tbMembers;
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

	public Set getTbMembers() {
		return this.tbMembers;
	}

	public void setTbMembers(Set tbMembers) {
		this.tbMembers = tbMembers;
	}

}