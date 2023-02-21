package com.codestates.hello_world;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

//클라이언트가 Request Body로 전달하는 “Hello, World” 문자열을 바인딩하는 DTO 클래스.
@Getter
public class MessagePostDto {
    @NotBlank
    private String message;
}

