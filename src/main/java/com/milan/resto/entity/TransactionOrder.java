package com.milan.resto.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
public class TransactionOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Integer transactionId;

    @Column(name = "table_id")
    private Integer tableId;

    @Column(name = "invoice_customer_name")
    private String invoiceCustomerName;

    @Column(name = "invoice_total_bill")
    private BigDecimal invoiceTotalBill;

    @Column(name = "transaction_cash")
    private BigDecimal transactionCash;

    @Column(name = "transaction_change")
    private BigDecimal transactionChange;
}
