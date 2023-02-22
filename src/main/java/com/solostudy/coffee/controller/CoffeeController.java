package com.solostudy.coffee.controller;

import com.solostudy.coffee.dto.CoffeePatchDto;
import com.solostudy.coffee.dto.CoffeePostDto;
import com.solostudy.coffee.dto.CoffeeResponseDto;
import com.solostudy.coffee.entity.Coffee;
import com.solostudy.coffee.mapper.CoffeeMapper;
import com.solostudy.coffee.service.CoffeeService;
import com.solostudy.utils.UriCreator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v10/coffees")
@Validated
public class CoffeeController {

    private final static String COFFEE_DEFAULT_URL = "/v10/coffees";
    private final CoffeeService coffeeService;
    private final CoffeeMapper coffeeMapper;

    public CoffeeController(CoffeeService coffeeService, CoffeeMapper coffeeMapper){
        this.coffeeService = coffeeService;
        this.coffeeMapper = coffeeMapper;
    }
    @PostMapping
    public ResponseEntity postCoffee(@Valid @RequestBody CoffeePostDto coffeePostDto) {
        Coffee coffee = coffeeService.createCoffee(coffeeMapper.coffeePostDtoToCoffee(coffeePostDto));
        URI location = UriCreator.createUri(COFFEE_DEFAULT_URL, coffee.getCoffeeId());

        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{coffee-id}") //price변경만. 이름바꾸고싶으면 아래 주석처리 후 요청 Json에 같이 넣으면 된다.
    public ResponseEntity patchCoffee(@PathVariable("coffee-id") @Positive long coffeeId,
                                      @Valid @RequestBody CoffeePatchDto coffeePatchDto) {
        coffeePatchDto.setCoffeeId(coffeeId);

        Coffee response = coffeeService.updateCoffee(coffeeMapper.coffeePatchDtoToCoffee(coffeePatchDto));

        return new ResponseEntity<>(coffeeMapper.coffeeToCoffeeResponseDto(response), HttpStatus.OK);
    }

    @GetMapping("/{coffee-id}")
    public ResponseEntity getCoffee(@PathVariable("coffee-id") long coffeeId) {
        Coffee response = coffeeService.findCoffee(coffeeId);

        return new ResponseEntity<>(coffeeMapper.coffeeToCoffeeResponseDto(response), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getCoffees() {
        List<Coffee> coffees = coffeeService.findCoffees();

        // 매퍼를 이용해서 List<Coffee>를 CoffeeResponseDtos로 변환
        List<CoffeeResponseDto> response = coffeeMapper.coffeesToCoffeeResponseDtos(coffees);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 커피 정보 삭제를 위한 핸들러 서드 구현
    @DeleteMapping("/{coffeeId}")
    public ResponseEntity deleteCoffee(@PathVariable("coffee-Id")long coffeeId){
        coffeeService.deleteCoffee(coffeeId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




}
