package com.mo.responseentity.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// @RestControllerAdvice
// public class ExceptionControllerAdvice {
//
//     /**
//      * 예외 처리 핸들러
//      *
//      * @param e
//      * @return
//      */
//     @ExceptionHandler(AbstractException.class)
//     public ResponseEntity<ErrorResponse> exception(AbstractException e) {
//
//         ErrorResponse response = ErrorResponse.builder()
//                 .status(e.statusCode())
//                 .message(e.getMessage())
//                 .build();
//         return ResponseEntity.status(e.statusCode()).body(response);
//     }
// }
