package com.solostudy.member.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
//MemberResponseDto 클래스는 응답 데이터의 역할을 해주는 DTO 클래스
public class MemberResponseDto {
    private long memberId;
    private String email;
    private String name;
    private String phone;
}