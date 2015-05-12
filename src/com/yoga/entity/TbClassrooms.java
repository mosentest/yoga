package com.yoga.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * TbClassrooms entity
 */

public class TbClassrooms implements java.io.Serializable {

	// Fields

	private String classroomsId;
	private String classroomsName;
	private Boolean classroomsState;
	private Set tbStaffCourseClassroomses = new HashSet(0);

	// Constructors

	/** default constructor */
	public TbClassrooms() {
	}

	/** full constructor */
	public TbClassrooms(String classroomsName, Boolean classroomsState,
			Set tbStaffCourseClassroomses) {
		this.classroomsName = classroomsName;
		this.classroomsState = classroomsState;
		this.tbStaffCourseClassroomses = tbStaffCourseClassroomses;
	}

	// Property accessors

	public String getClassroomsId() {
		return this.classroomsId;
	}

	public void setClassroomsId(String classroomsId) {
		this.classroomsId = classroomsId;
	}

	public String getClassroomsName() {
		return this.classroomsName;
	}

	public void setClassroomsName(String classroomsName) {
		this.classroomsName = classroomsName;
	}

	public Boolean getClassroomsState() {
		return this.classroomsState;
	}

	public void setClassroomsState(Boolean classroomsState) {
		this.classroomsState = classroomsState;
	}

	public Set getTbStaffCourseClassroomses() {
		return this.tbStaffCourseClassroomses;
	}

	public void setTbStaffCourseClassroomses(Set tbStaffCourseClassroomses) {
		this.tbStaffCourseClassroomses = tbStaffCourseClassroomses;
	}

}