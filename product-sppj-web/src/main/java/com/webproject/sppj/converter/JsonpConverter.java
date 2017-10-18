package com.webproject.sppj.converter;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.fasterxml.jackson.core.JsonEncoding;

public class JsonpConverter extends MappingJackson2HttpMessageConverter {

	private String callback;

	@Override
	protected void writeInternal(Object object, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes()).getRequest();
		callback = request.getParameter("callback");
		
		if (StringUtils.isEmpty(callback)) {
			super.writeInternal(object, outputMessage);
		} else {
			String result = callback + "("
					+ objectMapper.writeValueAsString(object) + ")";
			MediaType type = getDefaultContentType(object);
			JsonEncoding jsonEncoding = getJsonEncoding(type);
			IOUtils.write(result, outputMessage.getBody(), jsonEncoding.getJavaName());
		}
	}

	public String getCallback() {
		return callback;
	}

	public void setCallback(String callback) {
		this.callback = callback;
	}

}
