package com.webproject.sppj.service;

import java.util.List;

import com.webproject.sppj.bean.ProductComment;

public interface CommentService {
	
	/**
	 * 添加评价
	 * @param comment
	 * @return
	 */
	public boolean addComment(ProductComment comment);
	
	/**
	 * 删除评价
	 * @param id
	 * @return
	 */
	public boolean deleteComment(Integer id);
	
	/**
	 * 修改评价
	 * @param comment
	 * @return
	 */
	public boolean updateComment(ProductComment comment);
	
	/**
	 * 查找评价
	 * @param id
	 * @return
	 */
	public ProductComment queryComment(Integer id);
	
	/**
	 * 分页查询所有评价
	 * @return
	 */
	public List<ProductComment> queryList(Integer pageNum, Integer pageSize, ProductComment productComment);
	
	/**
	 * 根据标签查询评价列表
	 * @return
	 */
	public List<ProductComment> queryCommentListByLabel(String labelString);
	
}
