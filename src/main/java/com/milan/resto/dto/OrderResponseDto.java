package com.milan.resto.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderResponseDto {

    private Integer tableId;

    private Integer menuId;

    private String menuName;

    private Integer quantity;

    private String orderDescription;

    private BigDecimal menuPrice;
}
