package com.webproject.sppj.bean;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class CommentCount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;// 主键
	private Integer productId;// 商品id
	private Integer totalComments = 0;// 全部评价
	private Integer numOfGoodComments = 0;// 好评
	private Integer numOfMidComments = 0;// 中评
	private Integer numOfBadComments = 0;// 差评
	private Integer numOfHaveImageComments = 0;// 有图的评价

	public CommentCount() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getTotalComments() {
		return totalComments;
	}

	public void setTotalComments(Integer totalComments) {
		this.totalComments = totalComments;
	}

	public Integer getNumOfGoodComments() {
		return numOfGoodComments;
	}

	public void setNumOfGoodComments(Integer numOfGoodComments) {
		this.numOfGoodComments = numOfGoodComments;
	}

	public Integer getNumOfMidComments() {
		return numOfMidComments;
	}

	public void setNumOfMidComments(Integer numOfMidComments) {
		this.numOfMidComments = numOfMidComments;
	}

	public Integer getNumOfBadComments() {
		return numOfBadComments;
	}

	public void setNumOfBadComments(Integer numOfBadComments) {
		this.numOfBadComments = numOfBadComments;
	}

	public Integer getNumOfHaveImageComments() {
		return numOfHaveImageComments;
	}

	public void setNumOfHaveImageComments(Integer numOfHaveImageComments) {
		this.numOfHaveImageComments = numOfHaveImageComments;
	}

	@Override
	public String toString() {
		return "CommentCount [id=" + id + ", productId=" + productId
				+ ", totalComments=" + totalComments + ", numOfGoodComments="
				+ numOfGoodComments + ", numOfMidComments=" + numOfMidComments
				+ ", numOfBadComments=" + numOfBadComments
				+ ", numOfHaveImageComments=" + numOfHaveImageComments + "]";
	}

}
