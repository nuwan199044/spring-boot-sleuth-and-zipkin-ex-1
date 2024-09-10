package com.myapp.inventory_service.controller;

import com.myapp.inventory_service.dto.InventoryDTO;
import com.myapp.inventory_service.service.InventoryService;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
@Slf4j
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping
    @Observed(
            name = "user.name",
            contextualName = "inventory",
            lowCardinalityKeyValues = {"userType","userType2"}
    )
    public ResponseEntity<List<InventoryDTO>> createInventory(@RequestBody List<InventoryDTO> inventoryDTOs) {
        log.info("request received to the InventoryController {} ", inventoryDTOs.size());
        return new ResponseEntity<>(inventoryService.createInventory(inventoryDTOs), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<InventoryDTO>> getInventory() {
        return new ResponseEntity<>(inventoryService.getInventory(), HttpStatus.OK);
    }
}
