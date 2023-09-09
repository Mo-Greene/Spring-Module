package com.mo.messege.message.service;

import static org.junit.jupiter.api.Assertions.*;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mo.messege.message.dto.request.MessageRequest;

@SpringBootTest
class MessageServiceTest {

	@Autowired
	MessageService messageService;

	@Test
	@DisplayName("sendSms 메서드 테스트")
	void sendSms() throws UnsupportedEncodingException, NoSuchAlgorithmException, URISyntaxException, InvalidKeyException, JsonProcessingException {
		MessageRequest request = MessageRequest.builder()
			.to("01022745320").
			build();

		String response = messageService.sendSms(request);

		assertTrue(!response.equals("fail"));
	}
}
