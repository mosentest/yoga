package com.yoga.dto;

import java.io.Serializable;

public class MemberCourseDetailDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String memberId;// 会员
	private String courseId;// 课程

	public MemberCourseDetailDTO() {
		super();
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}


}
