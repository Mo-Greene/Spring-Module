package com.mo.messege.message.service;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.hc.client5.http.utils.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mo.messege.message.constant.NaverApi;
import com.mo.messege.message.dto.request.MessageRequest;
import com.mo.messege.message.dto.request.SmsRequest;
import com.mo.messege.message.dto.response.SmsResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MessageService {

	/**
	 * 인증번호 구현
	 * @param request
	 * @return
	 */
	public String sendSms(MessageRequest request) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException, URISyntaxException {

		String sixRandomNumber = makeRandomNumber();
		Long time = System.currentTimeMillis();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("x-ncp-apigw-timestamp", time.toString());
		headers.set("x-ncp-iam-access-key", NaverApi.ACCESS_KEY);
		headers.set("x-ncp-apigw-signature-v2", makeSignature(time));

		List<MessageRequest> messages = new ArrayList<>();
		messages.add(request);

		SmsRequest smsRequest = SmsRequest.builder()
			.type("SMS")
			.contentType("COMM")
			.countryCode("82")
			.from(NaverApi.SENDER_PHONE)
			.content("인증번호는 [" + sixRandomNumber + "] 입니다.")
			.messages(messages)
			.build();

		ObjectMapper objectMapper = new ObjectMapper();
		String body = objectMapper.writeValueAsString(smsRequest);

		HttpEntity<String> httpBody = new HttpEntity<>(body, headers);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());

		SmsResponse response =  restTemplate.postForObject(new URI("https://sens.apigw.ntruss.com/sms/v2/services/" + NaverApi.SERVICE_ID + "/messages"), httpBody, SmsResponse.class);

		if (Objects.requireNonNull(response).getStatusCode().equals("202")) {
			return sixRandomNumber;
		} else {
			return response.getStatusName();
		}
	}

	/**
	 * 난수 생성 메서드
	 * @return
	 */
	private String makeRandomNumber() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			sb.append((int) (Math.random() * 9));
		}
		return sb.toString();
	}

	/**
	 * sms signature 생성
	 * @param time
	 * @return
	 */
	public String makeSignature(Long time) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
		String space = " ";					// one space
		String newLine = "\n";					// new line
		String method = "POST";					// method
		String url = "/sms/v2/services/" + NaverApi.SERVICE_ID + "/messages";	// url (include query string)
		String timestamp = time.toString();			// current timestamp (epoch)
		String accessKey = NaverApi.ACCESS_KEY;			// access key id (from portal or Sub Account)
		String secretKey = NaverApi.SECRET_KEY;

		String message = new StringBuilder()
			.append(method)
			.append(space)
			.append(url)
			.append(newLine)
			.append(timestamp)
			.append(newLine)
			.append(accessKey)
			.toString();

		SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
		Mac mac = Mac.getInstance("HmacSHA256");
		mac.init(signingKey);

		byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));

		return Base64.encodeBase64String(rawHmac);
	}

}
