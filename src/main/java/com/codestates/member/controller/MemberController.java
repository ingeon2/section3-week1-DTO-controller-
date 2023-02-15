package com.codestates.member.controller;


import com.codestates.member.service.MemberService;
import com.codestates.member.dto.MemberPatchDto;
import com.codestates.member.dto.MemberPostDto;
import com.codestates.member.dto.MemberResponseDto;
import com.codestates.member.entity.Member;
import org.springframework.http.HttpStatus;
import com.codestates.member.mapper.MemberMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@RestController //@RestController 가 추가된 클래스는 애플리케이션 로딩 시, Spring Bean으로 등록
                //@RestController 를 추가하면 해당 클래스가 REST API의 리소스(자원, Resource)를 처리하기 위한 API 엔드포인트로 동작함을 정의
@RequestMapping(value = "/v5/members") //@RequestMapping 은 클라이언트의 요청과 클라이언트 요청을 처리하는 핸들러 메서드(Handler Method)를 매핑해주는 역할

public class MemberController {

    private final MemberService memberService; //서비스로직
    private final MemberMapper mapper; //DTO 클래스와 Member 간에 서로 타입을 변환

    public MemberController(MemberService memberService, MemberMapper mapper){
        this.memberService = memberService;
        this.mapper = mapper;
    }
    
    // 회원 정보 등록
    @PostMapping
    public ResponseEntity postMember(@Valid @RequestBody MemberPostDto memberDto) {

        // 매퍼를 이용해서 MemberPostDto를 Member로 변환
        Member member = mapper.memberPostDtoToMember(memberDto);

        Member response = memberService.createMember(member);

        //매퍼를 이용해서 Member를 MemberResponseDto로 변환
        return new ResponseEntity<>(mapper.memberToMemberResponseDto(response), HttpStatus.CREATED);
    }

    // 회원 정보 수정 (폰번호 수정)
    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(@PathVariable("member-id") @Positive long memberId, //여기가 url옆에 /member-id 로 붙여줄 내용.
                                      //http://localhost:8080/v1/members/15 이런식.
                                      @Valid @RequestBody MemberPatchDto memberPatchDto){

        memberPatchDto.setMemberId(memberId);

        // 매퍼를 이용해서 MemberPatchDto를 Member로 변환
        Member response = memberService.updateMember(mapper.memberPatchDtoToMember(memberPatchDto));

        // 매퍼를 이용해서 Member를 MemberResponseDto로 변환
        return new ResponseEntity<>(mapper.memberToMemberResponseDto(response), HttpStatus.OK);

    }

    // 한명 회원 정보 조회
    @GetMapping("/{member-id}")
    public ResponseEntity getMember(@PathVariable("member-id") long memberId) {
        Member response = memberService.findMember(memberId);

        // 매퍼를 이용해서 Member를 MemberResponseDto로 변환
        return new ResponseEntity<>(mapper.memberToMemberResponseDto(response), HttpStatus.OK);
    }

    // 모든 회원 정보 조회
    @GetMapping
    public ResponseEntity getMembers() {

        List<Member> members = memberService.findMembers();

        // 매퍼를 이용해서 List<Member>를 MemberResponseDto로 변환
        List<MemberResponseDto> response = members.stream()
                        .map(member -> mapper.memberToMemberResponseDto(member))
                        .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 회원 정보 삭제
    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(@PathVariable("member-id") @Positive long memberId){

        System.out.println("# delete member");
        memberService.deleteMember(memberId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}

//클라이언트의 요청을 전달 받아서 처리하기 위해서는 요청 핸들러 메서드(Request Handler Method)가 필요하다.
//Spring MVC에서는 HTTP Method 유형과 매치되는 @GetMapping, @PostMapping 등의 애너테이션을 지원한다.
//@PathVariable 애너테이션을 사용하면 클라이언트 요청 URI에 패턴 형식으로 지정된 변수의 값을 파라미터로 전달받을 수 있다.
//@RequestParam 애너테이션을 사용하면 쿼리 파라미터(Query Parmeter 또는 Query string), 폼 데이터(form-data), x-www-form-urlencoded 형식의 데이터를 파라미터로 전달 받을 수 있다.
//@GetMapping, @PostMapping 등에서 URI를 생략하면 클래스 레벨의 URI 경로만으로 요청 URI를 구성한다.