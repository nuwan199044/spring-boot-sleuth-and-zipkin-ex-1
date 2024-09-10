package com.myapp.order_service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class InventoryDTO {

    private String productCode;
    private Integer quantity;

}
