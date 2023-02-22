package com.solostudy.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Positive;

@Getter
@AllArgsConstructor
public class OrderCoffeeDto { //여러잔을 위해 (오더커피디티오 클래스 만듦)
    @Positive
    private long coffeeId;

    @Positive
    private int quantity;

    public long getCoffeeId() {
        return coffeeId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setCoffeeId(long coffeeId) {
        this.coffeeId = coffeeId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
