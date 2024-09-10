package com.myapp.order_service.feign;

import com.myapp.order_service.dto.InventoryDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "inventory-service", url = "http://localhost:8181/inventory")
public interface InventoryClient {

    @PostMapping
    List<InventoryDTO> createInventory(@RequestBody List<InventoryDTO> inventoryDTOs);
}
