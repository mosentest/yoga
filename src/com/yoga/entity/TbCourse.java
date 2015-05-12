package com.yoga.entity;

import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TbCourse entity
 */

public class TbCourse implements java.io.Serializable {

	// Fields

	private String courseId;
	private TbCourseType tbCourseType;
	private String couresName;
	private Date courseDate;
	private Time courseTime1;
	private Time courseTime2;
	private String coursePrice;
	private Set tbStaffCourseClassroomses = new HashSet(0);
	private Set tbMemberCourseDetails = new HashSet(0);

	// Constructors

	/** default constructor */
	public TbCourse() {
	}

	/** full constructor */
	public TbCourse(TbCourseType tbCourseType, String couresName,
			Date courseDate, Time courseTime1, Time courseTime2,
			String coursePrice, Set tbStaffCourseClassroomses,
			Set tbMemberCourseDetails) {
		this.tbCourseType = tbCourseType;
		this.couresName = couresName;
		this.courseDate = courseDate;
		this.courseTime1 = courseTime1;
		this.courseTime2 = courseTime2;
		this.coursePrice = coursePrice;
		this.tbStaffCourseClassroomses = tbStaffCourseClassroomses;
		this.tbMemberCourseDetails = tbMemberCourseDetails;
	}

	// Property accessors

	public String getCourseId() {
		return this.courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public TbCourseType getTbCourseType() {
		return this.tbCourseType;
	}

	public void setTbCourseType(TbCourseType tbCourseType) {
		this.tbCourseType = tbCourseType;
	}

	public String getCouresName() {
		return this.couresName;
	}

	public void setCouresName(String couresName) {
		this.couresName = couresName;
	}

	public Date getCourseDate() {
		return this.courseDate;
	}

	public void setCourseDate(Date courseDate) {
		this.courseDate = courseDate;
	}

	public Time getCourseTime1() {
		return this.courseTime1;
	}

	public void setCourseTime1(Time courseTime1) {
		this.courseTime1 = courseTime1;
	}

	public Time getCourseTime2() {
		return this.courseTime2;
	}

	public void setCourseTime2(Time courseTime2) {
		this.courseTime2 = courseTime2;
	}

	public String getCoursePrice() {
		return this.coursePrice;
	}

	public void setCoursePrice(String coursePrice) {
		this.coursePrice = coursePrice;
	}

	public Set getTbStaffCourseClassroomses() {
		return this.tbStaffCourseClassroomses;
	}

	public void setTbStaffCourseClassroomses(Set tbStaffCourseClassroomses) {
		this.tbStaffCourseClassroomses = tbStaffCourseClassroomses;
	}

	public Set getTbMemberCourseDetails() {
		return this.tbMemberCourseDetails;
	}

	public void setTbMemberCourseDetails(Set tbMemberCourseDetails) {
		this.tbMemberCourseDetails = tbMemberCourseDetails;
	}

}