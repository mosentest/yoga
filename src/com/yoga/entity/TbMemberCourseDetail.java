package com.yoga.entity;

/**
 * TbMemberCourseDetail entity
 */

public class TbMemberCourseDetail implements java.io.Serializable {

	// Fields

	private Integer id;
	private TbMemberCourse tbMemberCourse;
	private TbCourse tbCourse;

	// Constructors

	/** default constructor */
	public TbMemberCourseDetail() {
	}

	/** full constructor */
	public TbMemberCourseDetail(TbMemberCourse tbMemberCourse, TbCourse tbCourse) {
		this.tbMemberCourse = tbMemberCourse;
		this.tbCourse = tbCourse;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TbMemberCourse getTbMemberCourse() {
		return this.tbMemberCourse;
	}

	public void setTbMemberCourse(TbMemberCourse tbMemberCourse) {
		this.tbMemberCourse = tbMemberCourse;
	}

	public TbCourse getTbCourse() {
		return this.tbCourse;
	}

	public void setTbCourse(TbCourse tbCourse) {
		this.tbCourse = tbCourse;
	}

}