package com.webproject.sppj.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.webproject.sppj.bean.CommentCount;
import com.webproject.sppj.bean.ProductComment;
import com.webproject.sppj.service.CommentService;
import com.webproject.sppj.utils.FastDFSClientUtils;

@Controller
public class CommentController {

	@Autowired
	private CommentService commentService;

	@Value("${imgUrl}")
	private String imgUrl;

	@RequestMapping(value = "tocomment", method = RequestMethod.GET)
	public ModelAndView toAdd() {
		ModelAndView mv = new ModelAndView("product_comment");
		mv.addObject("labelList", commentService.getLabels());
		return mv;
	}

	/**
	 * 分页查询评价
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "showcomments/{pageNum}/{pageSize}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> queryCommentList(
			@PathVariable("pageNum") Integer pageNum,
			@PathVariable("pageSize") Integer pageSize) {

		try {
			Map<String, Object> map = new HashMap<String, Object>();
			List<ProductComment> list = commentService.queryList(pageNum,
					pageSize, null);
			CommentCount count = commentService.getCount();
			map.put("commentCount", count);
			map.put("productCommentList", list);
			return ResponseEntity.ok(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
				null);
	}

	/**
	 * 根据标签查询评价
	 * 
	 * @param labelString
	 * @return
	 */
	@RequestMapping(value = "{labelString}", method = RequestMethod.GET)
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
	 * 
	 * @param comment
	 * @return
	 */
	@RequestMapping(value = "evaluate", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Void> addComment(
			@RequestParam("score") Integer score,
			@RequestParam("labels") String[] labels,
			@RequestParam("experience") String experience, MultipartFile file,
			HttpServletRequest request, HttpServletResponse response) {

		ProductComment comment = new ProductComment();
		comment.setDataOfComment(new Date());
		comment.setExperience(experience);

		if (labels != null && labels.length > 1) {
			StringBuilder label = new StringBuilder();
			for (int i = 0; i < labels.length; i++) {
				label.append(labels[i] + ",");
			}

			comment.setLabel(label.deleteCharAt(label.length() - 1).toString());
		}
		comment.setScore(score);
		comment.setUserId(1);
		comment.setProductId(666);

		try {
			if (file != null) {
				// 检查文件是否是图片
				String filename = file.getOriginalFilename();
				FastDFSClientUtils upload = new FastDFSClientUtils();

				String extName = upload.getExtName(filename);
				if (!"jpg".equalsIgnoreCase(extName)) {
					// 没有图片
					comment.setImgPath("");

				} else {

					// 上传文件
					String uploadImageUrl = imgUrl;
					String[] uploadFile = upload.uploadFile(file.getBytes(),
							filename);
					for (String string : uploadFile) {
						uploadImageUrl = uploadImageUrl + "/" + string;
					}

					comment.setImgPath(uploadImageUrl);
				}
			}

			// 添加评价
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
	 * 
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
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<Boolean> deleteComment(@PathVariable("id") Integer id) {

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
