package com.yoga.entity;


public class TbMember implements java.io.Serializable {

	// Fields

	private String memberId;
	private TbMemberType tbMemberType;
	private String memberUsername;
	private String memberName;
	private Boolean memberSex;
	private String memberCard;
	private String memberPhone;
	private String memberAddress;
//	private Set tbMemberCourses = new HashSet(0);
//	private Set tbMemberConsumes = new HashSet(0);

	// Constructors

	/** default constructor */
	public TbMember() {
	}

	/** full constructor */
	public TbMember(TbMemberType tbMemberType, String memberUsername,
			String memberName, Boolean memberSex, String memberCard,
			String memberPhone, String memberAddress/*, Set tbMemberCourses,
			Set tbMemberConsumes*/) {
		this.tbMemberType = tbMemberType;
		this.memberUsername = memberUsername;
		this.memberName = memberName;
		this.memberSex = memberSex;
		this.memberCard = memberCard;
		this.memberPhone = memberPhone;
		this.memberAddress = memberAddress;
//		this.tbMemberCourses = tbMemberCourses;
//		this.tbMemberConsumes = tbMemberConsumes;
	}

	// Property accessors

	public String getMemberId() {
		return this.memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public TbMemberType getTbMemberType() {
		return this.tbMemberType;
	}

	public void setTbMemberType(TbMemberType tbMemberType) {
		this.tbMemberType = tbMemberType;
	}

	public String getMemberUsername() {
		return this.memberUsername;
	}

	public void setMemberUsername(String memberUsername) {
		this.memberUsername = memberUsername;
	}

	public String getMemberName() {
		return this.memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public Boolean getMemberSex() {
		return this.memberSex;
	}

	public void setMemberSex(Boolean memberSex) {
		this.memberSex = memberSex;
	}

	public String getMemberCard() {
		return this.memberCard;
	}

	public void setMemberCard(String memberCard) {
		this.memberCard = memberCard;
	}

	public String getMemberPhone() {
		return this.memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public String getMemberAddress() {
		return this.memberAddress;
	}

	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}

//	public Set getTbMemberCourses() {
//		return this.tbMemberCourses;
//	}
//
//	public void setTbMemberCourses(Set tbMemberCourses) {
//		this.tbMemberCourses = tbMemberCourses;
//	}
//
//	public Set getTbMemberConsumes() {
//		return this.tbMemberConsumes;
//	}
//
//	public void setTbMemberConsumes(Set tbMemberConsumes) {
//		this.tbMemberConsumes = tbMemberConsumes;
//	}

}