package com.yoga.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * TbConsume entity. @author MyEclipse Persistence Tools
 */

public class TbConsume implements java.io.Serializable {

	// Fields

	private String consumeId;
	private String consumeName;
	private String consumePrice;
	private Set tbMemberConsumeDetails = new HashSet(0);

	// Constructors

	/** default constructor */
	public TbConsume() {
	}

	/** full constructor */
	public TbConsume(String consumeName, String consumePrice,
			Set tbMemberConsumeDetails) {
		this.consumeName = consumeName;
		this.consumePrice = consumePrice;
		this.tbMemberConsumeDetails = tbMemberConsumeDetails;
	}

	// Property accessors

	public String getConsumeId() {
		return this.consumeId;
	}

	public void setConsumeId(String consumeId) {
		this.consumeId = consumeId;
	}

	public String getConsumeName() {
		return this.consumeName;
	}

	public void setConsumeName(String consumeName) {
		this.consumeName = consumeName;
	}

	public String getConsumePrice() {
		return this.consumePrice;
	}

	public void setConsumePrice(String consumePrice) {
		this.consumePrice = consumePrice;
	}

	public Set getTbMemberConsumeDetails() {
		return this.tbMemberConsumeDetails;
	}

	public void setTbMemberConsumeDetails(Set tbMemberConsumeDetails) {
		this.tbMemberConsumeDetails = tbMemberConsumeDetails;
	}

}