package com.milan.resto.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MenuResponseDto {

    private Integer menuId;

    private String menuName;

    private BigDecimal menuPrice;

    private Integer menuStock;
}
