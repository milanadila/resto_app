package com.milan.resto.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionResponseDto {

    private Integer transactionId;

    private Integer tableId;

    private String invoiceCustomerName;

    private BigDecimal invoiceTotalBill;

    private BigDecimal transactionCash;

    private BigDecimal transactionChange;
}
