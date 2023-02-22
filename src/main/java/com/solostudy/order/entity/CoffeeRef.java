package com.solostudy.order.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Builder
@Table("ORDER_COFFEE") //데이터베이스 테이블 매핑
public class CoffeeRef { //CoffeeRef 클래스는 ORDER_COFFEE 테이블과 매핑 되는 엔티티 클래스
    @Id
    private long orderCoffeeId;
    private long coffeeId;
    private int quantity;
}
//CoffeeRef 클래스는 주문 애그리거트 내에 있는 엔티티 클래스입니다. (루트는 아녀!)
//COFFEE 클래스는 커피 애그리거트 내에 있는 엔티티 클래스이자 애그리거트 루트입니다. (둘다만족)
//따라서 애그리거트 간의 매핑 규칙을 따르기 때문에 Member 클래스와 Order 클래스에서 했던 것처럼
//AggregateReference로 coffeeId를 감싸야 될 것 같지만 N 대 N 관계에서는 AggregateReference로 coffeeId를 감쌀 필요가 없습니다.
