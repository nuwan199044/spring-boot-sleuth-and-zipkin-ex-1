package com.myapp.inventory_service.service;

import com.myapp.inventory_service.dto.InventoryDTO;

import java.util.List;

public interface InventoryService {
    List<InventoryDTO> createInventory(List<InventoryDTO> inventoryDTOs);

    List<InventoryDTO> getInventory();
}
