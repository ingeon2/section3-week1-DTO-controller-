package com.codestates.coffee;

import com.codestates.member.MemberPatchDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/coffees")
public class CoffeeController {
    @PostMapping
    public ResponseEntity postOrder(@RequestBody CoffeePostDto coffeePostDto) {
        return new ResponseEntity<>(coffeePostDto, HttpStatus.CREATED);
    }

    @PatchMapping("/{coffee-id}") //price변경만. 이름바꾸고싶으면 아래 주석처리 후 요청 Json에 같이 넣으면 된다.
    public ResponseEntity patchCoffee(@PathVariable("coffee-id") long coffeeId,
                                      @RequestBody CoffePatchDto coffePatchDto) {
        coffePatchDto.setCoffeeId(coffeeId);
        coffePatchDto.setKorName("바닐라 라떼");
        coffePatchDto.setEngName("Vanilla Latte");

        return new ResponseEntity(coffePatchDto, HttpStatus.OK);
    }

    @GetMapping("/{coffee-id}")
    public ResponseEntity getCoffee(@PathVariable("coffee-id") long coffeeId) {
        System.out.println("# coffeeId: " + coffeeId);

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getOrders() {
        System.out.println("# get Coffees");

        return new ResponseEntity(HttpStatus.OK);
    }
}
