package com.example.grocerybooking.repository;

import com.example.grocerybooking.model.GroceryItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroceryItemRepository extends JpaRepository<GroceryItem, Long> {
    List<GroceryItem> findByQuantityGreaterThan(int i);

    List<GroceryItem> findByQuantityLessThan(int i);
}
