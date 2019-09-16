package com.milan.resto.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class TableResto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "table_id")
    private Integer tableId;

    @Column(name = "table_number")
    private Integer tableNumber;

    @Column(name = "table_capacity")
    private Integer tableCapacity;

    @Column(name = "table_availability")
    private Boolean tableAvailability;
}
