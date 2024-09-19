package net.mlorenzo.orderservice.controller;

import net.mlorenzo.basedomains.dto.Order;
import net.mlorenzo.orderservice.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String placeOrder(@RequestBody Order order) {
        orderService.placeOrder(order);
        return "Order placed";
    }
}
