package com.milan.resto.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class InvoiceResponseDto {

    private Integer invoiceId;

    private Integer tableId;

    private String invoiceCustomerName;

    private BigDecimal invoiceSubtotal;

    private BigDecimal invoiceTax;

    private BigDecimal invoiceServiceCharge;

    private BigDecimal invoiceTotalBill;

    private BigDecimal invoiceDiscount;

    private Boolean invoiceStatusPayment;
}
