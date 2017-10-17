package com.webproject.sppj.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.webproject.sppj.utils.FastDFSClientUtils;

@Controller
public class ImgUpload {
	
	@Value("${imgUrl}")
	private String imgUrl;

	@RequestMapping(value = "upload", method = RequestMethod.POST)
	public Map<String, Object> upload(MultipartFile file,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			//检查文件是否是图片
			String filename = file.getOriginalFilename();
			FastDFSClientUtils upload = new FastDFSClientUtils();
			
			String extName = upload.getExtName(filename);
			if (!"jpg".equalsIgnoreCase(extName)) {
				map.put("status", "fail");
				return map;
			}
			
			//上传文件
			String uploadImageUrl = imgUrl;
			String[] uploadFile = upload.uploadFile(file.getBytes(), filename);
			for (String string : uploadFile) {
				uploadImageUrl = uploadImageUrl + "/" + string; 
			}
			
			map.put("status", "success");
			map.put("imgUrl", uploadImageUrl);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", "error");
		}
		
		return map;
	}

}
