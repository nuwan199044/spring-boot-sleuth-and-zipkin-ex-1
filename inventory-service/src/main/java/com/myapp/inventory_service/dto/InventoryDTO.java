package com.myapp.inventory_service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryDTO {

    private String productCode;
    private Integer quantity;

}
