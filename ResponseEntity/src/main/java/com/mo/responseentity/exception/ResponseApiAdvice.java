package com.mo.responseentity.exception;

import java.util.Collections;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.mo.responseentity.util.ResponseApi;

@RestControllerAdvice
public class ResponseApiAdvice implements ResponseBodyAdvice<Object> {

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
		Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
		ServerHttpResponse response) {

		if (body == null) {
			body = Collections.EMPTY_LIST;
		}

		return ResponseApi.builder()
			.status(200)
			.data(body)
			.build();
	}

	@Override
	public boolean supports(MethodParameter returnType, Class converterType) {
		return true;
	}
}
