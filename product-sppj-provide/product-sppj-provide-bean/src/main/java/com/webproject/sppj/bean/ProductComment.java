package com.webproject.sppj.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ProductComment implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer commentId;
	private Integer userId;// 用户ID
	private Integer productId;// 商品ID
	private Integer score;// 评星
	private String label;// 标签
	private String experience;// 心得
	private String imgPath;// 图片地址
	private Date dataOfComment;// 评论时间

	public ProductComment() {
	}

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public Date getDataOfComment() {
		return dataOfComment;
	}

	public void setDataOfComment(Date dataOfComment) {
		this.dataOfComment = dataOfComment;
	}

	@Override
	public String toString() {
		return "ProductComment [commentId=" + commentId + ", userId=" + userId
				+ ", productId=" + productId + ", score=" + score + ", label="
				+ label + ", experience=" + experience + ", imgPath=" + imgPath
				+ ", dataOfComment=" + dataOfComment + "]";
	}

}
