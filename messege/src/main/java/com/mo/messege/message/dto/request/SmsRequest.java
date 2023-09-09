package com.mo.messege.message.dto.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SmsRequest {

	private String type;
	private String contentType;
	private String countryCode;
	private String from;
	private String content;
	private List<MessageRequest> messages;

}
