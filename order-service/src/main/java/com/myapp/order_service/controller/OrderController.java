package com.myapp.order_service.controller;

import com.myapp.order_service.dto.OrderDTO;
import com.myapp.order_service.service.OrderService;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @Observed(
            name = "user.name",
            contextualName = "order",
            lowCardinalityKeyValues = {"userType","userType2"}
    )
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        log.info("request received to the OrderController {} ", orderDTO.getCustomerCode());
        return new ResponseEntity<>(orderService.createOrder(orderDTO), HttpStatus.CREATED);
    }
}
