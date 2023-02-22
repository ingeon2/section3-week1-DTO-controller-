package com.solostudy.order.dto;

import com.solostudy.order.entity.Order;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
public class OrderResponseDto { //주문한 여러 건의 커피 정보를 응답으로 전송할 수 있도록 변경
                                //주문 시간과 주문 상태를 응답으로 전송할 수 있도록 변경
    private long orderId;
    private long memberId;
    private Order.OrderStatus orderStatus;
    private List<OrderCoffeeResponseDto> orderCoffees;
    private LocalDateTime createdAt;

    public long getOrderId() {
        return orderId;
    }

    public long getMemberId() {
        return memberId;
    }

    public Order.OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public List<OrderCoffeeResponseDto> getOrderCoffees() {
        return orderCoffees;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public void setOrderStatus(Order.OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setOrderCoffees(List<OrderCoffeeResponseDto> orderCoffees) {
        this.orderCoffees = orderCoffees;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
