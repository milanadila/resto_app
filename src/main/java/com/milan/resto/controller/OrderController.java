package com.milan.resto.controller;

import com.milan.resto.dto.OrderRequestDto;
import com.milan.resto.service.OrderService;
import com.milan.resto.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping
    ResponseEntity<Response> findAll() {
        Response response = new Response();
        response.setMessage("Success show all order");
        response.setData(orderService.findAll());

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<Response> findById(@PathVariable ("id") Integer id) {
        Response response = new Response();
        response.setMessage("Success show order by id");
        response.setData(orderService.findById(id));

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @PostMapping
    ResponseEntity<Response> doOrder(@RequestBody @Valid OrderRequestDto orderRequestDto) {
        Response response = new Response();
        response.setMessage("Success do order");
        response.setData(orderService.doOrder(orderRequestDto));

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }
}
