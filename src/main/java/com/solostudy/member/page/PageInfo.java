package com.solostudy.member.page;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PageInfo {
    private int page; //페이지 번호
    private int size; //한 페이지에 포함되는 데이터 row의 개수
    private int totalElements; //테이블에 저장되어 있는 데이터의 총 개수
    private int totalPages; //총 페이지 수
}
