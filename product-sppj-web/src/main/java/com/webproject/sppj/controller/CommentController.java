package com.webproject.sppj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.webproject.sppj.bean.ProductComment;
import com.webproject.sppj.service.CommentService;

@Controller
public class CommentController {

	@Autowired
	private CommentService commentService;

	/**
	 * 分页查询评价
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "${pageNum}/${pageSize}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<ProductComment>> queryCommentList(
			@PathVariable("pageNum") Integer pageNum,
			@PathVariable("pageSize") Integer pageSize) {

		try {
			List<ProductComment> list = commentService.queryList(pageNum,
					pageSize, null);
			return ResponseEntity.ok(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
				null);
	}

	/**
	 * 根据标签查询评价
	 * @param labelString
	 * @return
	 */
	@RequestMapping(value = "${labelString}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<ProductComment>> queryCommentListByLabel(
			@PathVariable("labelString") String labelString) {

		try {
			List<ProductComment> list = commentService
					.queryCommentListByLabel(labelString);
			return ResponseEntity.ok(list);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
				null);
	}

	/**
	 * 增加评价
	 * @param comment
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Void> addComment(ProductComment comment) {

		try {
			boolean addComment = commentService.addComment(comment);
			if (addComment) {
				return ResponseEntity.status(HttpStatus.CREATED).build();
			} else {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
				null);
	}

	/**
	 * 更新评价
	 * @param productComment
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<Boolean> updateComment(ProductComment productComment) {

		try {
			commentService.updateComment(productComment);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
				null);
	}
	
	/**
	 * 删除评价
	 * @param id
	 * @return
	 */
	@RequestMapping(value="${id}", method=RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<Boolean> deleteComment(@PathVariable("id")Integer id){
		
		try {
			commentService.deleteComment(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
				null);
	}

}
