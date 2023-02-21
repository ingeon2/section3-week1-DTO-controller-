package com.codestates.coffee.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class Coffee {
    @Id
    long coffeeId;

    String korName;
    String engName;
    int price;

    private String coffeCode; //Coffee의 중복 등록을 체크하기 위해 필요한 coffeeCode 멤버 변수

}
