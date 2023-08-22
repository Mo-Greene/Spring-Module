package com.mo.responseentity.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collections;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {

    private int status;
    private String message;
    private T data;


    /**
     * Response status 200
     *
     * @return EMPTY_LIST
     */
    public static Response<?> ok() {
        return Response.builder()
                .status(200)
                .message("success")
                .data(Collections.EMPTY_LIST)
                .build();
    }

    /**
     * Response status 200
     * @param data
     * @return data
     */
    public static <T> Response<?> ok(T data) {
        return Response.builder()
                .status(200)
                .message("success")
                .data(data)
                .build();
    }
}
