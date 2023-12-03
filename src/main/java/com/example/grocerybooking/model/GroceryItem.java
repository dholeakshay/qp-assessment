package com.example.grocerybooking.model;

import javax.persistence.*;

import lombok.Data;
@Entity
@Data
public class GroceryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(unique = true) // Ensure uniqueness based on the item name
    private String name;
    private double price;
    private int quantity;

}
