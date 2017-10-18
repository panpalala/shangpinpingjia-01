package com.webproject.sppj.service;

import java.util.List;

import com.webproject.sppj.bean.CommentCount;
import com.webproject.sppj.bean.ProductComment;
import com.webproject.sppj.bean.ProductLabel;

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
	 * 查询商品统计
	 * @return
	 */
	public CommentCount getCount();
	
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
	
	/**
	 * 获取所有的标签
	 * @return
	 */
	public List<ProductLabel> getLabels();
	
}
