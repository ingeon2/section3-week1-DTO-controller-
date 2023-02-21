package com.codestates.coffee.service;

import com.codestates.coffee.entity.Coffee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoffeeService {
    public Coffee createCoffee(Coffee coffee){


        //나중에 db생기면 저장하는 로직 추가
        Coffee createCoffee = coffee;
        return createCoffee;
    }

    public Coffee updateCoffee(Coffee coffee){


        //db생긴 후 업데이트 로직 추가
        Coffee updateCoffee = coffee;
        return updateCoffee;
    }

    public Coffee findCoffee (long coffeeId){


        //db 후 찾는 로직 추가
        Coffee coffee = new Coffee(coffeeId, "아메리카노", "Americano", 2500);
        return coffee;
    }


    public List<Coffee> findCoffees() {



        // db추가 후 객체는 나중에 DB에서 조회하는 로직 추가.
        List<Coffee> coffees = List.of(
                new Coffee(1, "아메리카노", "Americano", 2500),
                new Coffee(2, "콜드브루", "ColdBrew", 4000)
        );
        return coffees;
    }

    public void deleteCoffe(long coffeeId) {

        // db추가 후 삭제로직 추가
    }
}
