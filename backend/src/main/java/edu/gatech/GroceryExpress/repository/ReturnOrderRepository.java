package edu.gatech.GroceryExpress.repository;

import edu.gatech.GroceryExpress.entity.ReturnOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReturnOrderRepository extends JpaRepository<ReturnOrder, String> {
    List<ReturnOrder> findAll();
}


