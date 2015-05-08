package com.yoga.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * TbCourseType entity. @author MyEclipse Persistence Tools
 */

public class TbCourseType implements java.io.Serializable {

	// Fields

	private Integer id;
	private String type;
	private Set tbCourses = new HashSet(0);

	// Constructors

	/** default constructor */
	public TbCourseType() {
	}

	/** full constructor */
	public TbCourseType(String type, Set tbCourses) {
		this.type = type;
		this.tbCourses = tbCourses;
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

	public Set getTbCourses() {
		return this.tbCourses;
	}

	public void setTbCourses(Set tbCourses) {
		this.tbCourses = tbCourses;
	}

}