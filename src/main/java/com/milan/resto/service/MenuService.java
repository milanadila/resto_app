package com.milan.resto.service;

import com.milan.resto.dto.MenuResponseDto;
import com.milan.resto.entity.Menu;
import com.milan.resto.exception.IdNotFoundException;
import com.milan.resto.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MenuService {

    @Autowired
    MenuRepository menuRepository;

    public List<MenuResponseDto> findAll() {
        List<Menu> listMenu = menuRepository.findAll();
        List<MenuResponseDto> listMenuDto = new ArrayList<>();
        for (Menu menu: listMenu) {
            MenuResponseDto menuResponseDto = new MenuResponseDto();
            menuResponseDto.setMenuId(menu.getMenuId());
            menuResponseDto.setMenuName(menu.getMenuName());
            menuResponseDto.setMenuPrice(menu.getMenuPrice());

            listMenuDto.add(menuResponseDto);
        }

        return listMenuDto;
    }

    public Menu findById(Integer id) {

        Optional<Menu> menu = Optional.of(menuRepository.findById(id).orElseThrow(IdNotFoundException::new));
        Menu menuResponseDto = new Menu();
        menuResponseDto.setMenuId(menu.get().getMenuId());
        menuResponseDto.setMenuName(menu.get().getMenuName());
        menuResponseDto.setMenuPrice(menu.get().getMenuPrice());

        return menuResponseDto;
    }
}
