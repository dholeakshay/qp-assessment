package com.example.grocerybooking.dto;

import lombok.Data;

@Data
public class GroceryItemDTO {
    private Long id;
    private String name;
    private double price;
    private int quantity;
}
