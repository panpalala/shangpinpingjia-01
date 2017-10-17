package com.webproject.sppj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
