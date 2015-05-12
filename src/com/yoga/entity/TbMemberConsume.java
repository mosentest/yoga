package com.yoga.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * TbMemberConsume entity 消费订单
 */

public class TbMemberConsume implements java.io.Serializable {

	// Fields

	private String memberConsumeId;
	private TbMember tbMember;
	private Timestamp createTime;
	private String cost;
//	private Set tbMemberConsumeDetails = new HashSet(0);

	// Constructors

	/** default constructor */
	public TbMemberConsume() {
	}

	/** full constructor */
	public TbMemberConsume(TbMember tbMember, Timestamp createTime/*,
			String cost, Set tbMemberConsumeDetails*/) {
		this.tbMember = tbMember;
		this.createTime = createTime;
		this.cost = cost;
//		this.tbMemberConsumeDetails = tbMemberConsumeDetails;
	}

	// Property accessors

	public String getMemberConsumeId() {
		return this.memberConsumeId;
	}

	public void setMemberConsumeId(String memberConsumeId) {
		this.memberConsumeId = memberConsumeId;
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
//
//	public Set getTbMemberConsumeDetails() {
//		return this.tbMemberConsumeDetails;
//	}
//
//	public void setTbMemberConsumeDetails(Set tbMemberConsumeDetails) {
//		this.tbMemberConsumeDetails = tbMemberConsumeDetails;
//	}

}