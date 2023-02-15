package com.codestates.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
//MemberResponseDto 클래스는 응답 데이터의 역할을 해주는 DTO 클래스
public class MemberResponseDto {
    private long memberId;

    private String email;

    private String name;

    private String phone;
}