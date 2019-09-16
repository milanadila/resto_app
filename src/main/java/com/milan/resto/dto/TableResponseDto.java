package com.milan.resto.dto;

import lombok.Data;

@Data
public class TableResponseDto {

    private Integer tableId;

    private Integer tableNumber;

    private Integer tableCapacity;

    private Boolean tableAvailability;
}
