package com.yoga.entity;

import java.sql.Timestamp;

/**
 * TbStaffDetail entity
 */

public class TbStaffDetail implements java.io.Serializable {

	// Fields

	private Integer id;
	private TbStaff tbStaff;
	private String staffCard;
	private String staffAddress;
	private String staffEmail;
	private Timestamp staffTime;

	// Constructors

	/** default constructor */
	public TbStaffDetail() {
	}

	/** full constructor */
	public TbStaffDetail(TbStaff tbStaff, String staffCard,
			String staffAddress, String staffEmail, Timestamp staffTime) {
		this.tbStaff = tbStaff;
		this.staffCard = staffCard;
		this.staffAddress = staffAddress;
		this.staffEmail = staffEmail;
		this.staffTime = staffTime;
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

	public String getStaffCard() {
		return this.staffCard;
	}

	public void setStaffCard(String staffCard) {
		this.staffCard = staffCard;
	}

	public String getStaffAddress() {
		return this.staffAddress;
	}

	public void setStaffAddress(String staffAddress) {
		this.staffAddress = staffAddress;
	}

	public String getStaffEmail() {
		return this.staffEmail;
	}

	public void setStaffEmail(String staffEmail) {
		this.staffEmail = staffEmail;
	}

	public Timestamp getStaffTime() {
		return this.staffTime;
	}

	public void setStaffTime(Timestamp staffTime) {
		this.staffTime = staffTime;
	}

}