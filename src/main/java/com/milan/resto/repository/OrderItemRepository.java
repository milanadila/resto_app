package com.milan.resto.repository;

import com.milan.resto.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

    @Query(value = "select (sum(menu_price * quantity)) as total from order_item where table_id = :table_id group by table_id", nativeQuery = true)
    BigDecimal getTotalPrice(@Param("table_id") Integer id);


    void deleteById(Integer id);
}
