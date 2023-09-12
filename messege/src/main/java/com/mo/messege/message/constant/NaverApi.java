package com.mo.messege.message.constant;

import org.springframework.beans.factory.annotation.Value;

public class NaverApi {

	@Value("${naver-cloud.accessKey}")
	public static String ACCESS_KEY;

	@Value("${naver-cloud.secretKey}")
	public static String SECRET_KEY;

	@Value("${naver-cloud.serviceId}")
	public static String SERVICE_ID;

	@Value("${naver-cloud.senderPhone}")
	public static String SENDER_PHONE;
}
