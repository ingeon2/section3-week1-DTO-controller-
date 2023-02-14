package com.codestates.member;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController //@RestController 가 추가된 클래스는 애플리케이션 로딩 시, Spring Bean으로 등록
                //@RestController 를 추가하면 해당 클래스가 REST API의 리소스(자원, Resource)를 처리하기 위한 API 엔드포인트로 동작함을 정의
@RequestMapping(value = "/v1/members") //@RequestMapping 은 클라이언트의 요청과 클라이언트 요청을 처리하는 핸들러 메서드(Handler Method)를 매핑해주는 역할

public class MemberController {

    //매서드들 다 개선시키기 (개선 시 설명 붙여놓음)
    //Spring Framework에서 제공하는 클래스 중 HttpEntity라는 클래스가 존재한다.
    //이것은 HTTP 요청(Request) 또는 응답(Response)에 해당하는 HttpHeader와 HttpBody를 포함하는 클래스
    //HttpEntity 클래스를 상속받아 구현한 클래스가 RequestEntity, ResponseEntity 클래스
    
    
    // 회원 정보 등록
    @PostMapping
    public ResponseEntity postMember(@Valid @RequestBody MemberPostDto memberPostDto) { //Request Body가 JSON 형식이어야 함. (memberPostDto 객체)
//        // JSON 문자열 수작업을 Map 객체로 대체
//        Map<String, String> body = new HashMap<>();
//        body.put("email", email);
//        body.put("name", name);
//        body.put("phone", phone);
//
//        // 리턴 값을 ResponseEntity 객체로 변경
//        //new ResponseEntity<>(map, HttpStatus.CREATED); 처럼
//        //ResponseEntity 객체를 생성하면서 생성자 파라미터로 응답 데이터(map)와 HTTP 응답 상태를 함께 전달
//        //(HttpStatus.CREATED는 클라이언트의 POST 요청을 처리해서 요청 데이터(리소스)가 정상적으로 생성되었음을 의미하는 HTTP 응답 상태)
//        //리턴 값으로 단순히 Map 객체만 리턴해도 클라이언트 쪽에서는 정상적으로 JSON 형식의 응답 데이터를 받을 수 있음.
//        //그런데 ResponseEntity 객체로 응답 데이터를 래핑함으로써 조금 더 세련된 방식으로 응답 데이터를 생성했음.
//        return new ResponseEntity<Map>(body, HttpStatus.CREATED);

        //위의 로직은 매개변수로 @RequestParm 이메일,네임,폰 필요.
        //@RequestParam을 사용하는 대신에 DTO 클래스를 사용해서 postMember()에서는 MemberPostDto,
        // patchMember()에서는 MemberPatchDto 클래스의 객체를 통해서 Request Body를 한번에 전달 받을 수 있도록 수정
        return new ResponseEntity<>(memberPostDto, HttpStatus.CREATED);
    }

    // 회원 정보 수정 (폰번호 수정)
    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(@PathVariable("member-id") @Min(1) long memberId, //여기가 url옆에 /member-id 로 붙여줄 내용.
                                      //http://localhost:8080/v1/members/15 이런식.
                                      @Valid @RequestBody MemberPatchDto memberPatchDto){
        memberPatchDto.setMemberId(memberId);
        memberPatchDto.setName("이름 알아서 뭐하시게");
        //매개변수 멤버 id만 url옆에 붙여주고, 수정해줄 폰번호와 이름은 postman에서 patch요청보낼때 Json형식으로 작성.

        return new ResponseEntity<>(memberPatchDto, HttpStatus.OK);
    }

    // 한명 회원 정보 조회
    @GetMapping("/{member-id}")
    public ResponseEntity getMember(@PathVariable("member-id") long memberId) {
        System.out.println("# memberId: " + memberId);


        // 리턴 값을 ResponseEntity 객체로 변경
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 모든 회원 정보 조회
    @GetMapping
    public ResponseEntity getMembers() {
        System.out.println("# get Members");


        // 리턴 값을 ResponseEntity 객체로 변경
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 회원 정보 삭제
    @DeleteMapping("{member-id}")
    public ResponseEntity deleteMember(@PathVariable("member-id") long memberId){

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}

//클라이언트의 요청을 전달 받아서 처리하기 위해서는 요청 핸들러 메서드(Request Handler Method)가 필요하다.
//Spring MVC에서는 HTTP Method 유형과 매치되는 @GetMapping, @PostMapping 등의 애너테이션을 지원한다.
//@PathVariable 애너테이션을 사용하면 클라이언트 요청 URI에 패턴 형식으로 지정된 변수의 값을 파라미터로 전달받을 수 있다.
//@RequestParam 애너테이션을 사용하면 쿼리 파라미터(Query Parmeter 또는 Query string), 폼 데이터(form-data), x-www-form-urlencoded 형식의 데이터를 파라미터로 전달 받을 수 있다.
//@GetMapping, @PostMapping 등에서 URI를 생략하면 클래스 레벨의 URI 경로만으로 요청 URI를 구성한다.