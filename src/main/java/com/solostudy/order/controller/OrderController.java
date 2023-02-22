package com.solostudy.order.controller;


import com.solostudy.coffee.service.CoffeeService;
import com.solostudy.order.dto.OrderPostDto;
import com.solostudy.order.dto.OrderResponseDto;
import com.solostudy.order.entity.Order;
import com.solostudy.order.mapper.OrderMapper;
import com.solostudy.order.service.OrderService;
import com.solostudy.utils.UriCreator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/v10/orders")
@Validated
public class OrderController {

    private final static String ORDER_DEFAULT_URL = "/v10/orders"; // Default URL 경로
    private final OrderService orderService;
    private final OrderMapper mapper;
    private final CoffeeService coffeeService;

    public OrderController(OrderService orderService, OrderMapper mapper, CoffeeService coffeeService) {
        this.orderService = orderService;
        this.mapper = mapper;
        this.coffeeService = coffeeService;
    }

    @PostMapping
    public ResponseEntity postOrder(@Valid @RequestBody OrderPostDto orderPostDto) {
        Order order = orderService.createOrder(mapper.orderPostDtoToOrder(orderPostDto));

        //UriComponentsBuilder를 이용해 등록된 리소스(Default URL 경로)의 위치 정보인 URI 객체를 생성
        URI location = UriCreator.createUri(ORDER_DEFAULT_URL, order.getOrderId());
        // "/v10/orders/{order-id}"위치로 만드는것. (utils 패키지의 UriCreator 클래스와 합작)

        return ResponseEntity.created(location).build();
        //ResponseEntity.created(location).build(); 를 이용해 응답 객체를 리턴
    }

    @GetMapping("/{order-id}")
    public ResponseEntity getOrder(@PathVariable("order-id") @Positive long orderId) {
        Order order = orderService.findOrder(orderId);

        //주문 커피 정보 가져오기
        //CoffeeService 객체를 CoffeeMapper 매핑 메서드의 파라미터로 넘겨줌으로써
        //내부적으로 주문한 커피 정보를 OrderResponseDto에 포함시킬 수 있습니다.
        return new ResponseEntity<>(mapper.orderToOrderResponseDto(coffeeService, order), HttpStatus.OK);
        //아래 매서드에서 업데이트 한 내용은
        // 단일책임원칙. 커피서비스가 왜나와! 오더 컨트롤러에서
    }

//    @GetMapping("/{order-id}")
//    public ResponseEntity getOrder(@PathVariable("order-id") @Positive long orderId) {
//        Order order = orderService.findOrder(orderId);
//        List<Coffee> coffees = coffeeService.findOrderedCoffees(order);
//        return new ResponseEntity<>(mapper.orderToOrderResponseDto(order, coffees), HttpStatus.OK);
//    }

    @GetMapping
    public ResponseEntity getOrders() {
        List<Order> orders = orderService.findOrders();

        // 주문한 커피 정보 모두 가져오기
        List<OrderResponseDto> response =
                orders.stream()
                        .map(order -> mapper.orderToOrderResponseDto(coffeeService, order))
                        .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{order-id}")
    public ResponseEntity cancelOrder(@PathVariable("order-id") @Positive long orderId){
        orderService.cancelOrder(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}