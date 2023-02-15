package com.codestates.member.service;


import com.codestates.member.entity.Member;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //Spring Bean이 되기 위해 @Service 애너테이샨을 추가한 MemberService (컨트롤러에서 DI 사용하려고.)
public class MemberService { //서비스 구현 로직.
    public Member createMember(Member member){


        //나중에 db생기면 저장하는 로직 추가
        Member createMember = member;
        return createMember;
    }

    public Member updateMember(Member member){


        //db생긴 후 업데이트 로직 추가
        Member updateMember = member;
        return updateMember;
    }

    public Member findMember (long memberId){


        //db 후 찾는 로직 추가
        Member member = new Member(memberId, "hgd@gmail.com", "홍길동", "010-1234-5678");
        return member;
    }


    public List<Member> findMembers() {



        // db추가 후 객체는 나중에 DB에서 조회하는 로직 추가.
        List<Member> members = List.of(
                new Member(1, "hgd@gmail.com", "홍길동", "010-1234-5678"),
                new Member(2, "lml@gmail.com", "이몽룡", "010-1111-2222")
        );
        return members;
    }

    public void deleteMember(long memberId) {

        // db추가 후 삭제로직 추가
    }

}
