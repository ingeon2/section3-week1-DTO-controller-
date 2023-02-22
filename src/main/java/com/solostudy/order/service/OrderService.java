package com.solostudy.order.service;

import com.solostudy.coffee.service.CoffeeService;
import com.solostudy.exception.BusinessLogicException;
import com.solostudy.exception.ExceptionCode;
import com.solostudy.member.service.MemberService;
import com.solostudy.order.entity.Order;
import com.solostudy.order.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class OrderService {
    final private OrderRepository orderRepository;
    final private MemberService memberService;
    final private CoffeeService coffeeService;

    public OrderService(OrderRepository orderRepository, MemberService memberService, CoffeeService coffeeService) {
        this.orderRepository = orderRepository;
        this.memberService = memberService;
        this.coffeeService = coffeeService;
    }

    public Order createOrder(Order order) {
        // 회원이 존재하는지 확인
        memberService.findVerifiedMember(order.getMemberId().getId());

        // 커피가 존재하는지 조회해야 됨
        order.getOrderCoffees()
                .stream()
                .forEach(coffeeRef -> {
                    coffeeService.findVerifiedCoffee(coffeeRef.getCoffeeId()); //id 가져와서 유효한지 검증하는것
                });
        
        return orderRepository.save(order);
    }

    public Order findOrder(long orderId) {
        return findVerifiedOrder(orderId);
    }

    public List<Order> findOrders() {
        return (List<Order>) orderRepository.findAll();
    }

    
    public void cancelOrder(long orderId) { //주문 정보 취소
        Order findOrder = findVerifiedOrder(orderId);
        int step = findOrder.getOrderStatus().getStepNumber();

        // OrderStatus의 step이 2미만일 경우(ORDER_CONFIRM)에만 주문취소.
        if (step >= 2) {
            throw new BusinessLogicException(ExceptionCode.CANNOT_CHANGE_ORDER);
        }

        findOrder.setOrderStatus(Order.OrderStatus.ORDER_CANCEL);
        orderRepository.save(findOrder);
    }

    private Order findVerifiedOrder(long orderId) { //유효 오더인지 검증하고 맞으면 리턴해주는 매서드
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        Order findOrder =
                optionalOrder.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.ORDER_NOT_FOUND));
        //orElseThorow()는 optionalMember 객체가 null 이 아니라면 해당 객체를 리턴하고 null이라면 예외를 던짐.
        return findOrder;
    }
}
