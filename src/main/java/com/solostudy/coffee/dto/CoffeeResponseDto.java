package com.solostudy.coffee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//MemberResponseDto 클래스는 응답 데이터의 역할을 해주는 DTO 클래스
public class CoffeeResponseDto {
    private long coffeeId;

    private String korName;

    private String engName;

    private int price;

}
