package com.milan.resto.service;

import com.milan.resto.dto.TransactionRequestDto;
import com.milan.resto.dto.TransactionResponseDto;
import com.milan.resto.entity.InvoiceOrder;
import com.milan.resto.entity.TableResto;
import com.milan.resto.entity.TransactionOrder;
import com.milan.resto.exception.IdNotFoundException;
import com.milan.resto.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    TableRestoService tableRestoService;

    @Autowired
    InvoiceService invoiceService;

    public List<TransactionOrder> findAll() {
        return transactionRepository.findAll();
    }

    public TransactionOrder findById(Integer id) {
        return transactionRepository.findById(id).orElseThrow(IdNotFoundException::new);
    }

    public TransactionResponseDto doTransaction(TransactionRequestDto transactionRequestDto) throws Exception{

        InvoiceOrder invoice = invoiceService.findByTableId(transactionRequestDto.getTableId());
        BigDecimal totalBill = invoice.getInvoiceTotalBill();
        String customerName = invoice.getInvoiceCustomerName();
        Boolean status = invoice.getInvoiceStatusPayment();
        if (status == true) {
            throw new Exception("Invoice was paid");
        }
        invoice.setInvoiceStatusPayment(true);
        invoiceService.save(invoice);

        TableResto table = tableRestoService.findByTableNumber(transactionRequestDto.getTableId());
        table.setTableAvailability(true);
        tableRestoService.save(table);


        TransactionOrder transactionOrder = new TransactionOrder();
        transactionOrder.setTableId(transactionRequestDto.getTableId());
        transactionOrder.setInvoiceCustomerName(customerName);
        transactionOrder.setInvoiceTotalBill(totalBill);
        transactionOrder.setTransactionCash(transactionRequestDto.getTransactionCash());
        transactionOrder.setTransactionChange(transactionRequestDto.getTransactionCash().subtract(totalBill));
        transactionRepository.save(transactionOrder);

        TransactionResponseDto transactionResponseDto = new TransactionResponseDto();
        transactionResponseDto.setTransactionId(transactionOrder.getTransactionId());
        transactionResponseDto.setTableId(transactionOrder.getTableId());
        transactionResponseDto.setInvoiceCustomerName(transactionOrder.getInvoiceCustomerName());
        transactionResponseDto.setInvoiceTotalBill(transactionOrder.getInvoiceTotalBill());
        transactionResponseDto.setTransactionCash(transactionOrder.getTransactionCash());
        transactionResponseDto.setTransactionChange(transactionOrder.getTransactionChange());

        return transactionResponseDto;

    }
}
