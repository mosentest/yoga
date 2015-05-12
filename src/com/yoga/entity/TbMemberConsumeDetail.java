package com.yoga.entity;

/**
 * TbMemberConsumeDetail entity 消费订单详细表
 */

public class TbMemberConsumeDetail implements java.io.Serializable {

	// Fields

	private Integer id;
	private TbConsume tbConsume;
	private TbMemberConsume tbMemberConsume;

	// Constructors

	/** default constructor */
	public TbMemberConsumeDetail() {
	}

	/** full constructor */
	public TbMemberConsumeDetail(TbConsume tbConsume,
			TbMemberConsume tbMemberConsume) {
		this.tbConsume = tbConsume;
		this.tbMemberConsume = tbMemberConsume;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TbConsume getTbConsume() {
		return this.tbConsume;
	}

	public void setTbConsume(TbConsume tbConsume) {
		this.tbConsume = tbConsume;
	}

	public TbMemberConsume getTbMemberConsume() {
		return this.tbMemberConsume;
	}

	public void setTbMemberConsume(TbMemberConsume tbMemberConsume) {
		this.tbMemberConsume = tbMemberConsume;
	}

}