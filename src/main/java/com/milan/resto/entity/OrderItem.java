package com.milan.resto.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Integer orderId;

    @Column(name = "table_id")
    private Integer tableId;

    @Column(name = "menu_id")
    private Integer menuId;

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "menu_price")
    private BigDecimal menuPrice;

    @Column(name = "order_item_description")
    private String orderDescription;
}
