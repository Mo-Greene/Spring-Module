package com.mo.responseentity.controller;

import com.mo.responseentity.dto.BoardDTO;
import com.mo.responseentity.exception.CustomException;
import com.mo.responseentity.util.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardController {


    /**
     * response Test > return 빈 배열
     * @return
     */
    @PostMapping("/api")
    public Response<?> responsePostTest() {
        return Response.ok();
    }

    /**
     * response Test > 데이터 반환
     * @return
     */
    @GetMapping("/api")
    public Response<?> responseGetTest() {

        BoardDTO boardDTO = BoardDTO.builder()
                .title("title")
                .content("content")
                .build();

        return Response.ok(boardDTO);
    }

    /**
     * response Test > 에러 반환
     * @return
     */
    @GetMapping("/api/throw")
    public Response<?> responseThrow() {

        if (true) {
            throw new CustomException("Error Catch!");
        }

        return Response.ok();
    }
}
