package com.solostudy.member.dto;

import com.solostudy.validator.NotSpace;
import lombok.Getter;

import javax.validation.constraints.Pattern;
@Getter //이런 친구들이, 데이터 주고받을때 자동으로 겟 매서드 역할을 하는겨. 없다면 값을 못얻어가니 null 값으로 대체되겠지.
public class MemberPatchDto { //멤버 컨트롤러의 patchMember 매서드 사용시,
                              // Request Body를 전달 받을 때 사용하기 위한 MemberPatchDto 클래스
    private long memberId;

    @NotSpace(message = "회원 이름은 공백 안된다!")
    private String name;

    @NotSpace(message = "전화번호는 공백 안된다!")
    @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$",
            message = "휴대폰 번호는 010으로 시작하는 11자리 숫자와 '-'로 구성")
    private String phone;

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }
}
