package com.solostudy.hello_world;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class Message { // Message 라는 클래스 명은 데이터베이스의 테이블 명에 해당
    @Id //@Id 애너테이션을 추가한 멤버 변수는 해당 엔티티의 고유 식별자 역할을 하고, 이 식별자는 데이터베이스의 Primary key로 지정한 컬럼에 해당
    private long messageId;
    private String message;
}
