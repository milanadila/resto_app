package com.milan.resto.repository;

import com.milan.resto.entity.TransactionOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionOrder, Integer> {
}
