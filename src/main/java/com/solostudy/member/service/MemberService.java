package com.solostudy.member.service;


import com.solostudy.exception.BusinessLogicException;
import com.solostudy.exception.ExceptionCode;
import com.solostudy.member.entity.Member;
import com.solostudy.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service //Spring Bean이 되기 위해 @Service 애너테이샨을 추가한 MemberService (컨트롤러에서 DI 사용하려고.)
public class MemberService { //서비스 구현 로직.

    //DI
    private final MemberRepository memberRepository;
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    public Member createMember(Member member){
        //등록된 이메일인지 검증
        verifyExistEmail(member.getEmail());
        //회원 정보 저장
        return memberRepository.save(member);
    }

    public Member updateMember(Member member){
        //존재하는 회원인지 검증
        Member findMember = findVerifiedMember(member.getMemberId());
        //이름,번호 업데이트
        Optional.ofNullable(member.getName()) //이름 가져와서,
                .ifPresent(name -> findMember.setName(name)); //존재한다면 이렇게 바꿔라.
        Optional.ofNullable(member.getPhone())
                .ifPresent(phone -> findMember.setPhone(phone));
        //회원 정보 업데이트
        return memberRepository.save(findMember);
    }


    public Member findMember (long memberId){
        //특정 회원 정보 조회 (멤버 아이디로.)
        return findVerifiedMember(memberId);
    }


    public List<Member> findMembers() {
        //모든 회원 정보 조회
        return (List<Member>) memberRepository.findAll();
    }

    public void deleteMember(long memberId) {
        Member findMember = findVerifiedMember(memberId);

        memberRepository.delete(findMember);
    }

    //존재하는 회원인지 검증하는 매서드
    public Member findVerifiedMember(long memberId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Member findMember = optionalMember.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        //orElseThorow()는 optionalMember 객체가 null 이 아니라면 해당 객체를 리턴하고 null이라면 예외를 던짐.
        return findMember;
    }
    
    //이미 등록된 이메일 주소인지 검증 (private 이유는 이 클래스 안에서만 사용할 매서드라서.) ,오더서비스 커피서비스에서 버리파이는 보이드가 아님
    private void verifyExistEmail(String email){
        Optional<Member> member = memberRepository.findByEmail(email);
        if(member.isPresent()){
            throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
        }
    }

}
//그런데 신기한건 우리가 MemberRepository 인터페이스는 정의했지만 인터페이스의 구현 클래스는 별도로 구현을 한 적이 없습니다.
//이 MemberRepository 인터페이스의 구현 클래스는 누가 구현을 하는걸까요?
//바로 Spring Data JDBC에서 내부적으로 Java의 리플렉션 기술과 Proxy 기술을 이용해서 MemberRepository 인터페이스의 구현 클래스 객체를 생성