package com.solostudy.order.mapper;

import com.solostudy.coffee.entity.Coffee;
import com.solostudy.coffee.service.CoffeeService;
import com.solostudy.order.dto.OrderCoffeeResponseDto;
import com.solostudy.order.dto.OrderPostDto;
import com.solostudy.order.dto.OrderResponseDto;
import com.solostudy.order.entity.CoffeeRef;
import com.solostudy.order.entity.Order;
import org.mapstruct.Mapper;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface OrderMapper { //개발자가 직접 매핑 작업 코드를 구현하는 것으로 변경(이전엔 스프링이 구현해줬음)
    default Order orderPostDtoToOrder(OrderPostDto orderPostDto) {
        Order order = new Order();
        // orderPostDto에 포함된 memberId를 Order 클래스의 AggregateReference<Member, Long> memberId 타입과 일치 시키는 작업
        // orderPostDto의 memberId는 long 타입이고, Order 클래스의 memberId는 AggregateReference<Member, Long> 이므로 타입을 맞춰 줌.
        // IdOnlyAggregateReference 클래스는 AggregateReference 인터페이스의 구현 클래스이므로
        // long 타입인 memberId를 IdOnlyAggregateReference의 생성자 파라미터로 전달함으로써 타입을 맞춰줌.

        // Order 클래스의 멤버변수 memberId 자체가 AggregateReference<Member, Long> 타입임.
        order.setMemberId(new AggregateReference.IdOnlyAggregateReference(orderPostDto.getMemberId()));

        // orderPostDto에 포함된 주문한 커피 정보인 List<OrderCoffeeDto> orderCoffees 를
        // Java의 Stream을 이용해 Order 클래스의 Set<CoffeeRef> orderCoffees 으로 변환
        Set<CoffeeRef> orderCoffees = orderPostDto.getOrderCoffees()
                .stream()
                .map(orderCoffeeDto ->
                        // CoffeeRef 클래스에 @Builder 애너테이션이 적용되어 있으므로 lombok에서 지원하는 빌더 패턴을 사용
                        CoffeeRef.builder()
                                .coffeeId(orderCoffeeDto.getCoffeeId())
                                .quantity(orderCoffeeDto.getQuantity())
                                .build())
                .collect(Collectors.toSet());
        order.setOrderCoffees(orderCoffees);

        return order;
    }

    default OrderResponseDto orderToOrderResponseDto(CoffeeService coffeeService, Order order) {
        // Order의 memberId 필드가 AggregateReference<Member, Long> 타입이므로,
        // AggregateReference 에 포함된 구체적인 값인 long타입의 memberId를 얻음
        long memberId = order.getMemberId().getId();

        // 주문한 커피의 구체적인 정보를 조회하기 위해 orderCoffeesToOrderCoffeeResponseDtos(coffeeService, order.getOrderCoffees()); 를 호출
        List<OrderCoffeeResponseDto> orderCoffees = orderCoffeesToOrderCoffeeResponseDtos(coffeeService, order.getOrderCoffees());

        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setOrderCoffees(orderCoffees);
        orderResponseDto.setMemberId(memberId);
        orderResponseDto.setCreatedAt(order.getCreatedAt());
        orderResponseDto.setOrderId(order.getOrderId());
        orderResponseDto.setOrderStatus(order.getOrderStatus());

        // TODO 주문에 대한 더 자세한 정보로의 변환은 요구 사항에 따라 다를 수 있습니다.

        return orderResponseDto;
    }

    default List<OrderCoffeeResponseDto> orderCoffeesToOrderCoffeeResponseDtos(CoffeeService coffeeService, Set<CoffeeRef> orderCoffees) { //ㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁ
        // OrderCoffeesToOrderCoffeeResponseDtos()는 데이터베이스에서 커피의 구체적인 정보를 조회한 후, OrderCoffeeResponseDto에 커피 정보를 채워 넣는 역할
        // 파라미터로 전달받은 orderCoffees를 Java의 Stream을 이용해 데이터베이스에서 구체적인 커피 정보를 조회한 후, OrderCoffeeResponseDto로 변환하는 작업
        return orderCoffees.stream()
                .map(coffeeRef -> {
                    // 파라미터로 전달받은 coffeeService 객체를 이용해 coffeeId에 해당하는 Coffee를 조회
                    Coffee coffee = coffeeService.findCoffee(coffeeRef.getCoffeeId());

                    return new OrderCoffeeResponseDto(coffee.getCoffeeId(),
                            coffee.getKorName(),
                            coffee.getEngName(),
                            coffee.getPrice(),
                            coffeeRef.getQuantity());
                }).collect(Collectors.toList());
    }
}
