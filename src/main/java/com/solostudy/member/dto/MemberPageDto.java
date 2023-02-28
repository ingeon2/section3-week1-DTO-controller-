package com.solostudy.member.dto;

import com.solostudy.member.page.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MemberPageDto<T> { //전체 멤버의 정보를 담고있는 data 와 pageInfo 를 필드로 받는 Dto 클래스
    private T data;
    private PageInfo pageInfo;
}
