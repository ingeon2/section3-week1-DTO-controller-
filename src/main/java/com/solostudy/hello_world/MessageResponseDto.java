package com.solostudy.hello_world;

import lombok.Getter;
import lombok.Setter;

//Response에 사용할 DTO 클래스
@Getter
@Setter
public class MessageResponseDto {
    private long messageId;
    private String message;
}
