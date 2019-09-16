package com.milan.resto.controller;

import com.milan.resto.dto.TableRequestDto;
import com.milan.resto.service.TableRestoService;
import com.milan.resto.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "table")
public class TableController {

    @Autowired
    TableRestoService tableRestoService;

    @GetMapping
    ResponseEntity<Response> findAll() {
        Response response = new Response();
        response.setMessage("Success show all table");
        response.setData(tableRestoService.findAll());

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @GetMapping(value = "/{id")
    ResponseEntity<Response> getById(@PathVariable ("id") Integer id) {
        Response response = new Response();
        response.setMessage("Success show table by id");
        response.setData(tableRestoService.findById(id));

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @PostMapping
    ResponseEntity<Response> chooseTable(@RequestBody @Valid TableRequestDto tableRequestDto) {
        Response response = new Response();
        response.setMessage("Success choose table");
        response.setData(tableRestoService.chooseTable(tableRequestDto));

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }

}
