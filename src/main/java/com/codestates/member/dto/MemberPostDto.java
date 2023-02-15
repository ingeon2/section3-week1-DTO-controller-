package com.codestates.member.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class MemberPostDto { //멤버 컨트롤러의 postMember 매서드 사용시,
                             // Request Body를 전달 받을 때 사용하기 위한 MemberPostDto 클래스
                             //(get은 리소스를 조회, DTO 필요없음)
    //유효성 검증
    //에러일때 메세지 따로 없으면 디폴트 메세지

    @NotBlank
    @Email
    private String email;

    @NotBlank(message = "이름인데 이름이 없냐? 있어야지")
    private String name;

    @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$",
             message = "휴대폰 번호는 010으로 시작하는 11자리 숫자와 '-'로 구성")
    private String phone;

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
//DTO 클래스를 만들 때, 주의해야 할 부분은 멤버 변수 이외에 각 멤버 변수에 해당하는 getter 메서드가 있어야 한다는 것입니다.
//getter 메서드가 없으면 Response Body에 해당 멤버 변수의 값이 포함되지 않는 문제가 발생합니다.
//setter 메서드는 필수 항목은 아니지만 개발자의 필요에 의해서 있을 수도 있고, 없을 수도 있습니다.