package edu.gatech.GroceryExpress.repository;

import edu.gatech.GroceryExpress.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, String> {
    Item findByName(String name);

    List<Item> findAll();
}


