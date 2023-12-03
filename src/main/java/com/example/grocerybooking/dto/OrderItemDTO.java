package com.example.grocerybooking.dto;

import lombok.Data;

@Data
public class OrderItemDTO {
    private Long itemId;
    private int quantity;
}
