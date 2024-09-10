package com.myapp.inventory_service.service.impl;

import com.myapp.inventory_service.dto.InventoryDTO;
import com.myapp.inventory_service.entity.Inventory;
import com.myapp.inventory_service.repository.InventoryRepository;
import com.myapp.inventory_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<InventoryDTO> createInventory(List<InventoryDTO> inventoryDTOs) {
        log.info("creating inventory {} records", inventoryDTOs.size());
        List<Inventory> inventories = inventoryDTOs.stream()
                .map(inventoryDTO -> new Inventory(inventoryDTO.getProductCode(), inventoryDTO.getQuantity()))
                .toList();
        List<Inventory> savedInventory = inventoryRepository.saveAll(inventories);
        return savedInventory.stream()
                .map(inventory -> modelMapper.map(inventory, InventoryDTO.class))
                .toList();
    }

    @Override
    public List<InventoryDTO> getInventory() {
        log.info("fetch all inventory records");
        return inventoryRepository.findAll().stream()
                .map(inventory -> modelMapper.map(inventory, InventoryDTO.class))
                .toList();
    }
}
