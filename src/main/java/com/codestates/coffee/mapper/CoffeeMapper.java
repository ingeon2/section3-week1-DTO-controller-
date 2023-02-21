package com.codestates.coffee.mapper;

import com.codestates.coffee.dto.CoffeePatchDto;
import com.codestates.coffee.dto.CoffeePostDto;
import com.codestates.coffee.dto.CoffeeResponseDto;
import com.codestates.coffee.entity.Coffee;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CoffeeMapper {
    Coffee coffeePostDtoToCoffee(CoffeePostDto coffeePostDto);
    Coffee coffeePatchDtoToCoffee(CoffeePatchDto coffeePatchDto);
    CoffeeResponseDto coffeeToCoffeeResponseDto(Coffee coffee);
    List<CoffeeResponseDto> coffeesToCoffeeResponseDtos(List<Coffee> coffees);
}

//MemberMapperImpl 클래스는 언제, 어떻게 생성될까요?
//IntelliJ IDE의 오른쪽 상단의 [Gradle] 탭을 클릭한 후, [프로젝트 명 > Tasks 디렉토리 > build 디렉토리 > build task]를 더블 클릭하면 MapStruct로 정의된 인터페이스의 구현 클래스가 생성됩니다.
//
//MemberMapperImpl 클래스는 어디에 생성될까요?
//IntelliJ IDE의 좌측에서 [Project 탭 > 프로젝트명 > build] 디렉토리내의 MemberMapper 인터페이스가 위치한 패키지 안에 생성됩니다.