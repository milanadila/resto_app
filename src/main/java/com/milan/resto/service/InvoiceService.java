package com.milan.resto.service;

import com.milan.resto.dto.InvoiceRequestDto;
import com.milan.resto.dto.InvoiceResponseDto;
import com.milan.resto.entity.InvoiceOrder;
import com.milan.resto.entity.OrderItem;
import com.milan.resto.exception.IdNotFoundException;
import com.milan.resto.repository.InvoiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
public class InvoiceService {

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    OrderService orderService;

    public List<InvoiceOrder> findAll() {
        return invoiceRepository.findAll();
    }

    public InvoiceOrder findById(Integer id) {
        return invoiceRepository.findById(id).orElseThrow(IdNotFoundException::new);
    }

    public InvoiceResponseDto showInvoice(InvoiceRequestDto invoiceRequestDto) {
        BigDecimal orderItem = orderService.getTotalPrice(invoiceRequestDto.getTableId());
//        Integer menuId = orderItem.getMenuId();
//        Integer quantity = orderItem.getQuantity();
//        BigDecimal menuPrice = orderItem.getMenuPrice();
        BigDecimal subTotal = orderItem;
        log.info("SUBTOTAL :: " + subTotal);

//        BigDecimal subTotal = menuPrice.multiply(new BigDecimal(quantity));
        BigDecimal tax = subTotal.multiply(BigDecimal.valueOf(0.1));
        BigDecimal serviceCharge = subTotal.multiply(BigDecimal.valueOf(0.15));
        BigDecimal totalBill = subTotal.add(tax).add(serviceCharge);
        BigDecimal discount = BigDecimal.valueOf(15000);
        BigDecimal totalBillAfterDiscount = totalBill.subtract(discount);


        InvoiceOrder invoiceOrder = new InvoiceOrder();
        invoiceOrder.setTableId(invoiceRequestDto.getTableId());
        invoiceOrder.setInvoiceCustomerName(invoiceRequestDto.getInvoiceCustomerName());
        invoiceOrder.setInvoiceSubtotal(subTotal);
        invoiceOrder.setInvoiceTax(tax);
        invoiceOrder.setInvoiceServiceCharge(serviceCharge);
        invoiceOrder.setInvoiceDiscount(discount);
        invoiceOrder.setInvoiceTotalBill(totalBillAfterDiscount);
        invoiceOrder.setInvoiceStatusPayment(false);
        invoiceRepository.save(invoiceOrder);

        InvoiceResponseDto invoiceResponseDto = new InvoiceResponseDto();
        invoiceResponseDto.setInvoiceId(invoiceOrder.getInvoiceId());
        invoiceResponseDto.setTableId(invoiceOrder.getTableId());
        invoiceResponseDto.setInvoiceCustomerName(invoiceOrder.getInvoiceCustomerName());
        invoiceResponseDto.setInvoiceSubtotal(invoiceOrder.getInvoiceSubtotal());
        invoiceResponseDto.setInvoiceTax(invoiceOrder.getInvoiceTax());
        invoiceResponseDto.setInvoiceServiceCharge(invoiceOrder.getInvoiceServiceCharge());
        invoiceResponseDto.setInvoiceDiscount(invoiceOrder.getInvoiceDiscount());
        invoiceResponseDto.setInvoiceTotalBill(invoiceOrder.getInvoiceTotalBill());
        invoiceResponseDto.setInvoiceStatusPayment(invoiceOrder.getInvoiceStatusPayment());

        return invoiceResponseDto;

    }

    public InvoiceOrder findByTableId(Integer id) {
        return invoiceRepository.findByTableId(id);
    }

    public void save(InvoiceOrder invoiceOrder) {
        invoiceRepository.save(invoiceOrder);
    }
}
