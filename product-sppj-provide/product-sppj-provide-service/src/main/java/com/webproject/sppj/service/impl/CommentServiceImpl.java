package com.webproject.sppj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.webproject.sppj.bean.CommentCount;
import com.webproject.sppj.bean.ProductComment;
import com.webproject.sppj.bean.ProductLabel;
import com.webproject.sppj.mapper.CommentCountMapper;
import com.webproject.sppj.mapper.ProductCommentMapper;
import com.webproject.sppj.mapper.ProductLabelMapper;
import com.webproject.sppj.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private ProductCommentMapper commentMapper;

	@Autowired
	private ProductLabelMapper labelMapper;

	@Autowired
	private CommentCountMapper countMapper;

	@Override
	public boolean addComment(ProductComment comment) {
		commentMapper.insertSelective(comment);

		// 根据productId获取CommentCount
		CommentCount count = new CommentCount();
		count.setProductId(comment.getProductId());
		CommentCount tmp = countMapper.selectOne(count);

		// 如果数据库中不存在该商品的评价,创建一个数据
		if (tmp == null) {
			// bean类中初始化所有参数为0,不需要设置
			countMapper.insert(count);
		}

		if (comment.getImgPath() != null) {
			count.setNumOfHaveImageComments(count.getNumOfHaveImageComments() + 1);
		}

		// 更新评价
		Integer score = comment.getScore();
		switch (score) {
		case 1:
			count.setNumOfBadComments(count.getNumOfBadComments() + 1);
			break;
		case 2:
		case 3:
			count.setNumOfMidComments(count.getNumOfMidComments() + 1);
			break;
		case 4:
		case 5:
			count.setNumOfGoodComments(count.getNumOfGoodComments() + 1);
			break;
		}

		// 更新商品评价统计表
		countMapper.updateByPrimaryKey(count);

		return true;
	}

	@Override
	public boolean deleteComment(Integer id) {
		ProductComment tmp = commentMapper.selectByPrimaryKey(id);

		// 获取商品评价
		CommentCount count = new CommentCount();
		count.setProductId(tmp.getProductId());
		CommentCount x = countMapper.selectOne(count);

		// 去掉商品评价中的统计数据
		if (x != null) {
			Integer score = tmp.getScore();
			switch (score) {
			case 1:
				x.setNumOfBadComments(x.getNumOfBadComments() - 1);
				break;
			case 2:
			case 3:
				x.setNumOfMidComments(x.getNumOfMidComments() - 1);
				break;
			case 4:
			case 5:
				x.setNumOfGoodComments(x.getNumOfGoodComments() - 1);
				break;
			}
			x.setTotalComments(x.getTotalComments() - 1);
		}

		commentMapper.deleteByPrimaryKey(id);
		return false;
	}

	@Override
	public boolean updateComment(ProductComment comment) {
		Example example = new Example(ProductComment.class);
		//根据条件获取当前评价
		example.createCriteria().andEqualTo("userId", comment.getUserId())
				.andEqualTo("productId", comment.getProductId())
				.andEqualTo("dataOfComment", comment.getDataOfComment());

		commentMapper.updateByExampleSelective(comment, example);
		return false;
	}

	@Override
	public ProductComment queryComment(Integer id) {
		return commentMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<ProductComment> queryList(Integer pageNum, Integer pageSize, ProductComment productComment) {
		PageHelper.startPage(pageNum, pageSize);
		return commentMapper.select(productComment);
	}

	@Override
	public List<ProductComment> queryCommentListByLabel(String labelString) {
		//根据标签内容获取标签
		ProductLabel p = new ProductLabel();
		p.setLabelString(labelString);
		ProductLabel label = labelMapper.selectOne(p);
		
		//查询所有包含这个标签的评价
		Example example = new Example(ProductComment.class);
		//标签id以","分隔,因此前后加上","防止多位数重合
		example.createCriteria().andLike("label", "," + label.getId().toString() + ",");
		return commentMapper.selectByExample(example);
	}

}
