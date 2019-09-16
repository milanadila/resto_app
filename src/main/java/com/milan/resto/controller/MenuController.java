package com.milan.resto.controller;

import com.milan.resto.service.MenuService;
import com.milan.resto.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "menu")
public class MenuController {

    @Autowired
    MenuService menuService;

    @GetMapping
    ResponseEntity<Response> findAll() {
        Response response = new Response();
        response.setMessage("Success show all menu");
        response.setData(menuService.findAll());

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<Response> getById(@PathVariable ("id") Integer id) {
        Response response = new Response();
        response.setMessage("Success show menu by id");
        response.setData(menuService.findById(id));

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }
}
