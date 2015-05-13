package com.yoga.dto;

import java.io.Serializable;

public class MemberConsumeDetailDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String memberId;
	private String consumeId;
	private String consumeNum;
	
	public MemberConsumeDetailDTO(String memberId, String consumeId, String consumeNum) {
		super();
		this.memberId = memberId;
		this.consumeId = consumeId;
		this.consumeNum = consumeNum;
	}
	
	public MemberConsumeDetailDTO() {
		super();
	}


	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getConsumeId() {
		return consumeId;
	}
	public void setConsumeId(String consumeId) {
		this.consumeId = consumeId;
	}
	public String getConsumeNum() {
		return consumeNum;
	}
	public void setConsumeNum(String consumeNum) {
		this.consumeNum = consumeNum;
	}
	
	@Override
	public String toString() {
		return "MemberConsumeDetailDTO [memberId=" + memberId + ", consumeId=" + consumeId + ", consumeNum=" + consumeNum + "]";
	}
	
	
}
