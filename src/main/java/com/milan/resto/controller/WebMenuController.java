package com.milan.resto.controller;

import com.milan.resto.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/menu")
public class WebMenuController {

    @Autowired
    MenuService menuService;

    @GetMapping("listMenu")
    public ModelAndView getMenu() {
        ModelAndView mav = new ModelAndView("menu");
        mav.addObject("Menus", menuService.findAll());

        return mav;
    }
}
