package com.solostudy.coffee.repository;

import com.solostudy.coffee.entity.Coffee;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CoffeeRepository extends CrudRepository<Coffee, Long> {

    Optional<Coffee> findByCoffeeCode(String coffeeCode); //WHERE 절에서 COFFEE_CODE를 조건으로 질의하게 해주는 쿼리 메서드


    @Query("SELECT * FROM COFFEE WHERE COFFEE_ID = :coffeeId") // COFFEE 테이블에 질의하기 위해 @Query라는 애너테이션을 사용
    Optional<Coffee> findByCoffee(Long coffeeId);

    //@Query 애너테이션을 이용하면 SQL 쿼리문을 직접 작성할 수 있기 때문에 복잡한 쿼리문의 경우 @Query 애너테이션을 이용해서 직접 쿼리문을 작성 가능.

    //하지만 단순한 쿼리의 경우 Spring Data JDBC에서 지원하는 Query Method를 정의해서 사용하는 것이 간결한 코드 유지와 생산성 면에서 바람직하다고 생각.
}