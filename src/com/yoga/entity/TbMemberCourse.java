package com.yoga.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * TbMemberCourse entity
 */

public class TbMemberCourse implements java.io.Serializable {

	// Fields

	private String memberCourseId;
	private TbMember tbMember;
	private Timestamp createTime;
	private String cost;
//	private Set tbMemberCourseDetails = new HashSet(0);

	// Constructors

	/** default constructor */
	public TbMemberCourse() {
	}

	/** full constructor */
	public TbMemberCourse(TbMember tbMember, Timestamp createTime, String cost/*,
			Set tbMemberCourseDetails*/) {
		this.tbMember = tbMember;
		this.createTime = createTime;
		this.cost = cost;
//		this.tbMemberCourseDetails = tbMemberCourseDetails;
	}

	// Property accessors

	public String getMemberCourseId() {
		return this.memberCourseId;
	}

	public void setMemberCourseId(String memberCourseId) {
		this.memberCourseId = memberCourseId;
	}

	public TbMember getTbMember() {
		return this.tbMember;
	}

	public void setTbMember(TbMember tbMember) {
		this.tbMember = tbMember;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getCost() {
		return this.cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

//	public Set getTbMemberCourseDetails() {
//		return this.tbMemberCourseDetails;
//	}
//
//	public void setTbMemberCourseDetails(Set tbMemberCourseDetails) {
//		this.tbMemberCourseDetails = tbMemberCourseDetails;
//	}

}