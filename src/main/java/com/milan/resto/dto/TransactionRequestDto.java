package com.milan.resto.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionRequestDto {

    private Integer tableId;

    private BigDecimal transactionCash;
}
