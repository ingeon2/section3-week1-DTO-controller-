package com.solostudy.order.entity;

import com.solostudy.member.entity.Member;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Table("ORDERS") //‘Order’라는 단어는 SQL 쿼리문에서 사용하는 예약어이기 때문에 이와 같이 테이블 이름을 변경
public class Order {
    @Id
    private long orderId;

    private AggregateReference<Member, Long> memberId;
    //Member 클래스는 회원 애그리거트의 루트,
    //그리고 Order 클래스는 주문 애그리거트의 루트.
    //Member 클래스와 Order 클래스는 1대N의 관계 → 애그리거트 루트끼리 Id들로 참조하여 연결해준것 + 일대 다 관계 나타낸것.
    //1대N 관계의 애그리거트 루트 간 ID 참조는 AggregateReference 클래스로 한번 감싸주는 방법으로 구현

    @MappedCollection(idColumn = "ORDER_ID", keyColumn = "ORDER_COFFEE_ID")

    //엔티티 클래스 간에 연관 관계를 맺어주는 정보를 의미, idColumn 외래키, keyColumn 기본키
    private Set<CoffeeRef> orderCoffees = new LinkedHashSet<>();
    //Order 클래스는 애그리거트 루트,
    //Coffee 클래스는 애그리거트 루트.
    //1. N대N의 관계를 —> 1대N, N대 1의 관계로 변경
    //2. 1대N, N대 1의 관계를 CoffeeRef 같이 중간에서 ID를 참조하게 해주는 클래스를 통해 다시 1대N대1의 관계로 변경
    //그 엔티티 클래스가 바로 위에 있는 CoffeeRef라는 클래스



    private OrderStatus orderStatus = OrderStatus.ORDER_REQUEST; //주문 상태 정보 멤버변수
    private LocalDateTime createdAt = LocalDateTime.now(); //주문 등록 시간 멤버변수


    public enum OrderStatus{ //이눔 생성자까지 생성해서 오더의 멤버변수로 사용.
        //OrderStatus는 주문을 위한 전용 상태 값으로 사용하기 위해 Order 클래스의 멤버로 포함
        ORDER_REQUEST(1, "주문 요청"),
        ORDER_CONFIRM(2, "주문 확정"),
        ORDER_COMPLETE(3, "주문 완료"),
        ORDER_CANCEL(4, "주문 취소");

        @Getter
        private int stepNumber;

        @Getter
        private String stepDescription;

        OrderStatus(int stepNumber, String stepDescription){
            this.stepNumber = stepNumber;
            this.stepDescription = stepDescription;
        }
    }

    //여기까지 다 멤버변수를 위한 코드!!!!!!!!!!!
}
