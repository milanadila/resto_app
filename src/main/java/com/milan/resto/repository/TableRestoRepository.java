package com.milan.resto.repository;

import com.milan.resto.entity.TableResto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRestoRepository extends JpaRepository<TableResto, Integer> {
    TableResto findByTableNumber(Integer tableNumber);
}
