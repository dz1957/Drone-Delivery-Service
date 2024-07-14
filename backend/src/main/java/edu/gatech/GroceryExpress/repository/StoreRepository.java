package edu.gatech.GroceryExpress.repository;

import edu.gatech.GroceryExpress.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, String> {
    List<Store> findAll();
}


