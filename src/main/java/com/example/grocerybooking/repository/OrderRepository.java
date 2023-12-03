package com.example.grocerybooking.repository;

import com.example.grocerybooking.model.PlacedOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<PlacedOrder, Long> {
}
