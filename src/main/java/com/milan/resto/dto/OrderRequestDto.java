package com.milan.resto.dto;

import lombok.Data;

@Data
public class OrderRequestDto {

    private Integer tableId;

    private Integer menuId;

    private Integer quantity;

    private String orderDescription;
}
