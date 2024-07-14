package edu.gatech.GroceryExpress.repository;

import edu.gatech.GroceryExpress.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    Customer findByAccount(String name);
}


