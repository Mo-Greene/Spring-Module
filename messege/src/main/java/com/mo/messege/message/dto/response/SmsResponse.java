package com.mo.messege.message.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SmsResponse {

	private String requestId;
	private LocalDateTime requestTime;
	private String statusCode;
	private String statusName;
}
