package com.example.grocerybooking.controller;

import com.example.grocerybooking.exception.ResourceNotFoundException;
import com.example.grocerybooking.model.GroceryItem;
import com.example.grocerybooking.model.PlacedOrder;
import com.example.grocerybooking.repository.GroceryItemRepository;
import com.example.grocerybooking.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/items")
public class AdminController {

    @Autowired
    private GroceryItemRepository groceryItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    //Add new grocery items to the system
    @PostMapping
    public ResponseEntity<String> addGroceryItems(@RequestBody List<GroceryItem> items) {
        try {
            groceryItemRepository.saveAll(items);
            return new ResponseEntity<>("Grocery items added successfully", HttpStatus.CREATED);
        } catch (DataIntegrityViolationException ex) {
            // Handle unique constraint violation
            return new ResponseEntity<>("Error: One or more grocery items already exist", HttpStatus.CONFLICT);
        }
    }

    // View existing grocery items
    @GetMapping
    public List<GroceryItem> getGroceryItems() {
        return groceryItemRepository.findAll();
    }

    // Remove grocery items from the system
    @DeleteMapping("/{itemId}")
    public String removeGroceryItem(@PathVariable Long itemId) {
        groceryItemRepository.deleteById(itemId);
        return "Grocery item remove successfully";
    }

    //Update details (e.g., name, price) of existing grocery items
    @PutMapping("/{itemId}")
    public String updateGroceryItem(@PathVariable Long itemId, @RequestBody GroceryItem newItem) {
        if (groceryItemRepository.existsById(itemId)) {
        newItem.setId(itemId);
        groceryItemRepository.save(newItem);
            return "Grocery item updated successfully";
        }
        return "Grocery item not found";
    }

    @PutMapping("/{itemId}/manage-inventory")
    public ResponseEntity<String> manageInventory(
            @PathVariable Long itemId,
            @RequestParam int quantity) {
        try {
            GroceryItem item = groceryItemRepository.findById(itemId)
                    .orElseThrow(() -> new ResourceNotFoundException("Item not found"));

            if (quantity == 0) {
                return new ResponseEntity<>("Quantity to manage must be non-zero", HttpStatus.BAD_REQUEST);
            }

            int updatedQuantity = item.getQuantity() + quantity;

            if (updatedQuantity < 0) {
                return new ResponseEntity<>("Inventory cannot be negative", HttpStatus.BAD_REQUEST);
            }

            item.setQuantity(updatedQuantity);
            groceryItemRepository.save(item);

            return new ResponseEntity<>("Inventory managed successfully", HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>("Error: " + ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/low-quantity")
    public List<GroceryItem> getItemsWithLowQuantity() {
        return groceryItemRepository.findByQuantityLessThan(10);
    }
    @GetMapping("/orders")
    public List<PlacedOrder> getAllOrders() {
        return orderRepository.findAll();
    }
}
