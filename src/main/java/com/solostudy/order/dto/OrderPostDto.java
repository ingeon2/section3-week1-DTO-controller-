package com.solostudy.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@Getter
@AllArgsConstructor
public class OrderPostDto {
    @Positive
    private long memberId;

    //여러잔을 위해 (오더커피디티오 클래스 만듦)
    @Valid
    private List<OrderCoffeeDto> orderCoffees;

    public long getMemberId() {
        return memberId;
    }

    public List<OrderCoffeeDto> getOrderCoffees() {
        return orderCoffees;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public void setOrderCoffees(List<OrderCoffeeDto> orderCoffees) {
        this.orderCoffees = orderCoffees;
    }
}
