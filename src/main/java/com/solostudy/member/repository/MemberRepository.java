package com.solostudy.member.repository;

import com.solostudy.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


//Spring Data JDBC에서는 CrudRepository라는 인터페이스를 제공해주고 있으며 (기본적인 조회, 수정, 삭제)
// 이 CrudRepository의 기능을 사용하기 위해서 MemberRepository가 CrudRepository를 상속
public interface MemberRepository extends CrudRepository<Member, Long> {
    //바로 위 멤버는 멤버 엔티티클래스, Long은 멤버 엔티티클래스에서 @Id 에너테이션 붙은 멤버변수
    Optional<Member> findByEmail(String email);
    //쿼리 메서드(Query Method)는 내부적으로 아래의 SQL 쿼리문으로 변환되어 데이터베이스의 MEMBER 테이블에 질의를 보냄.
    //기본적인 사용법은 ‘find + By + SQL 쿼리문에서 WHERE 절의 컬럼명 + (WHERE 절 컬럼의 조건이 되는 데이터) ’ 형식
    //예를 들어 EMAIL 컬럼과 NAME 컬럼을 조건으로 지정하고 싶다면, findByEmailAndName(String email, String name)
    //이게 다 Spring Data JDBC 에서 지원.

    //만약 Member 엔티티 클래스에 firstName이라는 멤버 변수가 있고, 테이블에 있는 FIRST_NAME이라는 컬럼명과 매핑이 된다고 가정할 경우,
    // 쿼리메서드는 findByFirstName 이 되어야지 findByFIRST_NAME 이 되어서는 안됨. (Spring JDBC 입장에서는 엔티티 클래스를 바라보고 작업을 하기 때문)

    Page<Member> findAllByOrderByMemberIdDesc(Pageable pageable);
    //지알아서 매서드 이름 보고 내림차순, 즉 최신 데이터 순 으로 해줌.
}
