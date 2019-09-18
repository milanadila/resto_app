package com.milan.resto.controller;


import com.milan.resto.dto.TransactionRequestDto;
import com.milan.resto.service.TransactionService;
import com.milan.resto.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping
    ResponseEntity<Response> findAll() {
        Response response = new Response();
        response.setMessage("Success show all transaction");
        response.setData(transactionService.findAll());

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<Response> findById(@PathVariable ("id") Integer id) {
        Response response = new Response();
        response.setMessage("Success show transaction by id");
        response.setData(transactionService.findById(id));

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @PostMapping
    ResponseEntity<Response> doOrder(@RequestBody @Valid TransactionRequestDto transactionRequestDto) throws Exception{
        Response response = new Response();
        response.setMessage("Payment Success");
        response.setData(transactionService.doTransaction(transactionRequestDto));

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }
}
