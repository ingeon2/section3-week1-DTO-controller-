package com.solostudy.coffee.dto;

import lombok.Getter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
@Getter
public class CoffeePostDto {
    @NotBlank
    private String korName;

    @NotBlank
    @Pattern(regexp = "^([A-Za-z])(\\s?[A-Za-z])*$",
            message = "engName 영어로 작성해야지(단어 사이 공백 한 칸 포함). 예) Cafe Latte")
    private String engName;

    @Range(min = 100, max = 50000)
    private int price;


    @NotBlank
    @Pattern(regexp = "^([A-Za-z]){3}$", 
             message = "커피 코드는 3자리 영어라고")
    private String coffeeCode;

}

