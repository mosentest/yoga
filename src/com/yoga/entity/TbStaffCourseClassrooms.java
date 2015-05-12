package com.yoga.entity;

import java.sql.Timestamp;

/**
 * TbStaffCourseClassrooms entity
 */

public class TbStaffCourseClassrooms implements java.io.Serializable {

	// Fields

	private Integer id;
	private TbStaff tbStaff;
	private TbCourse tbCourse;
	private TbClassrooms tbClassrooms;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public TbStaffCourseClassrooms() {
	}

	/** full constructor */
	public TbStaffCourseClassrooms(TbStaff tbStaff, TbCourse tbCourse,
			TbClassrooms tbClassrooms, Timestamp createTime) {
		this.tbStaff = tbStaff;
		this.tbCourse = tbCourse;
		this.tbClassrooms = tbClassrooms;
		this.createTime = createTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TbStaff getTbStaff() {
		return this.tbStaff;
	}

	public void setTbStaff(TbStaff tbStaff) {
		this.tbStaff = tbStaff;
	}

	public TbCourse getTbCourse() {
		return this.tbCourse;
	}

	public void setTbCourse(TbCourse tbCourse) {
		this.tbCourse = tbCourse;
	}

	public TbClassrooms getTbClassrooms() {
		return this.tbClassrooms;
	}

	public void setTbClassrooms(TbClassrooms tbClassrooms) {
		this.tbClassrooms = tbClassrooms;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}