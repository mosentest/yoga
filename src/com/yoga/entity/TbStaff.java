package com.yoga.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * TbStaff entity. @author MyEclipse Persistence Tools
 */

public class TbStaff implements java.io.Serializable {

	// Fields

	private String staffId;
	private String staffName;
	private Boolean staffSex;
	private Short staffAge;
	private String staffPost;
	private String staffPhone;
	// private Boolean idDelete;
//	private Set tbStaffDetails = new HashSet(0);
//	private Set tbUsers = new HashSet(0);
//	private Set tbStaffCourseClassroomses = new HashSet(0);

	// Constructors

	/** default constructor */
	public TbStaff() {
	}

	/** full constructor */
	public TbStaff(String staffName, Boolean staffSex, Short staffAge, String staffPost,
			String staffPhone) {
		this.staffName = staffName;
		this.staffSex = staffSex;
		this.staffAge = staffAge;
		this.staffPost = staffPost;
		this.staffPhone = staffPhone;
		// this.idDelete = idDelete;
//		this.tbStaffDetails = tbStaffDetails;
//		this.tbUsers = tbUsers;
//		this.tbStaffCourseClassroomses = tbStaffCourseClassroomses;
	}

	// Property accessors

	public String getStaffId() {
		return this.staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getStaffName() {
		return this.staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public Boolean getStaffSex() {
		return this.staffSex;
	}

	public void setStaffSex(Boolean staffSex) {
		this.staffSex = staffSex;
	}

	public Short getStaffAge() {
		return this.staffAge;
	}

	public void setStaffAge(Short staffAge) {
		this.staffAge = staffAge;
	}

	public String getStaffPost() {
		return this.staffPost;
	}

	public void setStaffPost(String staffPost) {
		this.staffPost = staffPost;
	}

	public String getStaffPhone() {
		return this.staffPhone;
	}

	public void setStaffPhone(String staffPhone) {
		this.staffPhone = staffPhone;
	}

//	public Set getTbStaffDetails() {
//		return this.tbStaffDetails;
//	}
//
//	public void setTbStaffDetails(Set tbStaffDetails) {
//		this.tbStaffDetails = tbStaffDetails;
//	}
//
//	public Set getTbUsers() {
//		return this.tbUsers;
//	}
//
//	public void setTbUsers(Set tbUsers) {
//		this.tbUsers = tbUsers;
//	}
//
//	public Set getTbStaffCourseClassroomses() {
//		return this.tbStaffCourseClassroomses;
//	}
//
//	public void setTbStaffCourseClassroomses(Set tbStaffCourseClassroomses) {
//		this.tbStaffCourseClassroomses = tbStaffCourseClassroomses;
//	}

}