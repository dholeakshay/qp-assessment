package com.example.grocerybooking.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Order_table")
public class PlacedOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Long userId; // Assuming a user ID
    private Long itemId;
    private int quantity;

}
