package com.milan.resto.service;

import com.milan.resto.dto.OrderRequestDto;
import com.milan.resto.dto.OrderResponseDto;
import com.milan.resto.entity.Menu;
import com.milan.resto.entity.OrderItem;
import com.milan.resto.exception.IdNotFoundException;
import com.milan.resto.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    MenuService menuService;

    public List<OrderItem> findAll() {
        return orderItemRepository.findAll();
    }

    public OrderItem findById(Integer id) {
        return orderItemRepository.findById(id).orElseThrow(IdNotFoundException::new);
    }

    public OrderResponseDto doOrder(OrderRequestDto orderRequestDto) throws Exception{
        Menu menu = menuService.findById(orderRequestDto.getMenuId());
        String menuName = menu.getMenuName();
        BigDecimal menuPrice = menu.getMenuPrice();
        Integer stock = menu.getMenuStock();
        Integer quantity = orderRequestDto.getQuantity();

        Integer newStock = stock - quantity;
        if (newStock < 0) {
            throw new Exception("Food is not available");
        }
        menu.setMenuStock(newStock);
        menuService.save(menu);

        OrderItem orderItem = new OrderItem();
        orderItem.setTableId(orderRequestDto.getTableId());
        orderItem.setMenuId(orderRequestDto.getMenuId());
        orderItem.setMenuName(menuName);
        orderItem.setMenuPrice(menuPrice);
        orderItem.setQuantity(orderRequestDto.getQuantity());
        orderItem.setOrderDescription(orderRequestDto.getOrderDescription());
        orderItemRepository.save(orderItem);

        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setTableId(orderItem.getTableId());
        orderResponseDto.setMenuId(orderItem.getMenuId());
        orderResponseDto.setMenuName(menuName);
        orderResponseDto.setQuantity(orderItem.getQuantity());
        orderResponseDto.setMenuPrice(menuPrice);
        orderResponseDto.setOrderDescription(orderItem.getOrderDescription());

        return orderResponseDto;
    }

    public BigDecimal getTotalPrice(Integer id) {
        return orderItemRepository.getTotalPrice(id);
    }

    public OrderItem findByMenuIAndTableId(Integer menuId, Integer tableId) {
        return orderItemRepository.findByMenuIdAndTableId(menuId, tableId);
    }

    @Transactional
    public void cancelOrder(Integer menuId, Integer tableId) {
        orderItemRepository.findByMenuIdAndTableId(menuId, tableId);
        orderItemRepository.deleteByMenuIdAndTableId(menuId, tableId);
    }
}
