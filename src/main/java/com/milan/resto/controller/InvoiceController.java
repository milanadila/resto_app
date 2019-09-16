package com.milan.resto.controller;

import com.milan.resto.dto.InvoiceRequestDto;
import com.milan.resto.service.InvoiceService;
import com.milan.resto.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "invoice")
public class InvoiceController {

    @Autowired
    InvoiceService invoiceService;

    @GetMapping
    ResponseEntity<Response> findAll() {
        Response response = new Response();
        response.setMessage("Success show all invoice");
        response.setData(invoiceService.findAll());

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<Response> findById(@PathVariable ("id") Integer id) {
        Response response = new Response();
        response.setMessage("Success show invoice by id");
        response.setData(invoiceService.findById(id));

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @PostMapping
    ResponseEntity<Response> showInvoice(@RequestBody @Valid InvoiceRequestDto invoiceRequestDto) {
        Response response = new Response();
        response.setMessage("Success show invoice");
        response.setData(invoiceService.showInvoice(invoiceRequestDto));

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }
}
