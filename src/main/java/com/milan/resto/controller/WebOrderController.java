package com.milan.resto.controller;

import com.milan.resto.dto.OrderRequestDto;
import com.milan.resto.service.MenuService;
import com.milan.resto.service.OrderService;
import com.milan.resto.service.TableRestoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/order")
public class WebOrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    TableRestoService tableRestoService;

    @Autowired
    MenuService menuService;

    @GetMapping(value = "viewOrder")
    public ModelAndView getOrder() {
        ModelAndView mav = new ModelAndView("add-order");
        mav.addObject("Tables", tableRestoService.findAll());
        mav.addObject("Menus", menuService.findAll());
        mav.addObject("Orders", orderService.findAll());

        return mav;
    }

    @GetMapping(value = "addOrder")
    public ModelAndView addOrder() {
        ModelAndView mav = new ModelAndView("add-order");
        return mav;
    }

    @PostMapping(value = "addOrder")
    public ModelAndView addOrders(@Valid OrderRequestDto orderRequestDto) throws Exception{
        ModelAndView mav = new ModelAndView("detail-order");
        mav.addObject("Orders", orderService.doOrder(orderRequestDto));

        return mav;
    }
}
