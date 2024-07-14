package edu.gatech.GroceryExpress.repository;

import edu.gatech.GroceryExpress.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {
}


