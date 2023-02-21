package com.codestates.hello_world;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")//@Mapper는 단순히 '이것은 매퍼입니다!!'라는 것을 표시하기 위한 어노테이션
public interface MessageMapper {
    Message messageDtoToMessage(MessagePostDto messagePostDto);
    MessageResponseDto messageToMessageResponseDto(Message message);
}
