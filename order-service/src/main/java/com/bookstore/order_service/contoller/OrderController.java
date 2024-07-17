package com.bookstore.order_service.contoller;


import com.bookstore.order_service.dto.OrderRequest;
import com.bookstore.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
//    public String placeOrder(@RequestBody OrderRequest orderRequest) {
//            orderService.placeOrder(orderRequest);
//            return "Order Placed Successfully";
//    }
    public ResponseEntity<String> placeOrder(@RequestBody OrderRequest orderRequest) {
        System.out.println("OrderRequest received: " + orderRequest);
        orderService.placeOrder(orderRequest);
        return ResponseEntity.ok("Order placed successfully");
    }

}
