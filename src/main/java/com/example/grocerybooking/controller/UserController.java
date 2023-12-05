package com.example.grocerybooking.controller;

import com.example.grocerybooking.dto.GroceryItemDTO;
import com.example.grocerybooking.dto.OrderItemDTO;
import com.example.grocerybooking.exception.InsufficientInventoryException;
import com.example.grocerybooking.exception.ResourceNotFoundException;
import com.example.grocerybooking.model.GroceryItem;
import com.example.grocerybooking.model.OrderItem;
import com.example.grocerybooking.repository.GroceryItemRepository;
import com.example.grocerybooking.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.grocerybooking.model.PlacedOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private GroceryItemRepository groceryItemRepository;

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ModelMapper modelMapper;

    //View the list of available grocery items
    @GetMapping("/items")
    public List<GroceryItemDTO> getAvailableItems() {
        List<GroceryItem> availableItems = groceryItemRepository.findByQuantityGreaterThan(0);
        return availableItems.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // Utility method to convert GroceryItemDTO to GroceryItem
    private GroceryItemDTO convertToDto(GroceryItem groceryItem) {
        return modelMapper.map(groceryItem, GroceryItemDTO.class);
    }

    // Ability to book multiple grocery items in a single order
    @PostMapping("/order")
    public ResponseEntity<PlacedOrder> placeOrder(@RequestBody List<OrderItemDTO> orderItemsDtos) {
        try {
            List<PlacedOrder> placedOrders = new ArrayList<>();

            for (OrderItemDTO orderItemDTO : orderItemsDtos) {
                Long itemId = orderItemDTO.getItemId();
                int quantity = orderItemDTO.getQuantity();

                GroceryItem item = groceryItemRepository.findById(itemId)
                        .orElseThrow(() -> new ResourceNotFoundException("Item not found"));

                if (item.getQuantity() < quantity) {
                    throw new InsufficientInventoryException("Insufficient inventory for item: " + item.getName());
                }

                item.setQuantity(item.getQuantity() - quantity);
                groceryItemRepository.save(item);

                PlacedOrder placedOrder = modelMapper.map(orderItemDTO, PlacedOrder.class);
                placedOrder.setUserId(1L); // Assuming a user ID (replace with actual user ID)
                placedOrder.setQuantity(quantity);
                placedOrders.add(placedOrder);
            }

            orderRepository.saveAll(placedOrders);
            return new ResponseEntity<>(placedOrders.get(0), HttpStatus.CREATED); // Assuming one order for simplicity
        } catch (DataIntegrityViolationException ex) {
            // Handle Hibernate-specific exception (e.g., unique constraint violation)
            throw new RuntimeException("Data integrity violation: " + ex.getMessage());
        }
    }
    // Utility method to convert OrderItemDTO to OrderItem
    private OrderItem convertToEntity(OrderItemDTO orderItemDTO) {
        return modelMapper.map(orderItemDTO, OrderItem.class);
    }

    // Utility method to convert List<OrderItemDTO> to List<OrderItem>
    private List<OrderItem> convertToEntityList(List<OrderItemDTO> orderItemDTOs) {
        return orderItemDTOs.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
    }
}


