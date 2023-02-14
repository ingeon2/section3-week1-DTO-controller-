package com.codestates.coffee;

public class CoffeePostDto {
    private String engName;
    private String korName;
    private String price;

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public void setKorName(String korName) {
        this.korName = korName;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getEngName() {
        return engName;
    }

    public String getKorName() {
        return korName;
    }

    public String getPrice() {
        return price;
    }
}
