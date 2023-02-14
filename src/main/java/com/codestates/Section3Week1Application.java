package com.codestates;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //@Component가 붙은 클래스를 검색, Spring Bean으로 등록
					   //@Configuration 이 붙은 클래스를 자동으로 찾아주고, 추가적으로 Spring Bean을 등록하는 기능
					   //자동 구성을 활성화
public class Section3Week1Application {

	public static void main(String[] args) { //애플리케이션이 실행되기 전에 여러가지 설정 작업을 수행하여 실행 가능한 애플리케이션으로 만드는 역할
		SpringApplication.run(Section3Week1Application.class, args);
	}

}
