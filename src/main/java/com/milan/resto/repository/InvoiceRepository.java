package com.milan.resto.repository;

import com.milan.resto.entity.InvoiceOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceOrder, Integer> {
}
