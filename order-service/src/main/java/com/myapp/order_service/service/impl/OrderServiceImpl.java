package com.myapp.order_service.service.impl;

import com.myapp.order_service.dto.InventoryDTO;
import com.myapp.order_service.dto.OrderDTO;
import com.myapp.order_service.entity.Order;
import com.myapp.order_service.entity.OrderLineItem;
import com.myapp.order_service.feign.InventoryClient;
import com.myapp.order_service.repository.OrderRepository;
import com.myapp.order_service.service.OrderService;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final ModelMapper modelMapper;
    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        log.info("order creation process is started ");
        Order order = modelMapper.map(orderDTO, Order.class);
        List<InventoryDTO> inventoryDTOS = new ArrayList<>();
        for (OrderLineItem item : order.getLineItems()) {
            item.setOrder(order);
            inventoryDTOS.add(InventoryDTO.builder().productCode(item.getProductCode()).quantity(item.getQuantity()).build());
        }
        Order savedOrder = orderRepository.save(order);
        inventoryDTOS = inventoryClient.createInventory(inventoryDTOS);
        log.info("{} inventory record are created. ", inventoryDTOS.size());
        return modelMapper.map(savedOrder, OrderDTO.class);
    }
}
