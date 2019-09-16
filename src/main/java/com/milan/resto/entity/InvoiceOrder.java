package com.milan.resto.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
public class InvoiceOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_id")
    private Integer invoiceId;

    @Column(name = "table_id")
    private Integer tableId;

    @Column(name = "invoice_customer_name")
    private String invoiceCustomerName;

    @Column(name = "invoice_subtotal")
    private BigDecimal invoiceSubtotal;

    @Column(name = "invoice_tax")
    private BigDecimal invoiceTax;

    @Column(name = "invoice_service_charge")
    private BigDecimal invoiceServiceCharge;

    @Column(name = "invoice_total_bill")
    private BigDecimal invoiceTotalBill;

    @Column(name = "invoice_discount")
    private BigDecimal invoiceDiscount;

    @Column(name = "invoice_status_payment")
    private Boolean invoiceStatusPayment;
}
