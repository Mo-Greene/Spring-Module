package com.mo.responseentity.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
public class ErrorResponse {

    private int status;
    private String message;

    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private Map<String, String> validation;

}
