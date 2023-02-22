package com.solostudy.coffee.dto;

import com.solostudy.validator.NotSpace;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Pattern;
@Getter
public class CoffeePatchDto {
    private long coffeeId;

    @NotSpace(message = "커피명(한글)은 공백이면 되겠니?")
    private String korName;

    @Pattern(regexp = "^([A-Za-z])(\\s?[A-Za-z])*$", message = "engName인데 한글이 되겠니? 예) Cafe Latte")
    private String engName;

    @Range(min= 100, max= 50000)
    private Integer price;

    public void setCoffeeId(long coffeeId) {
        this.coffeeId = coffeeId;
    }

    public int getPrice() {
        return price;
    }
}
