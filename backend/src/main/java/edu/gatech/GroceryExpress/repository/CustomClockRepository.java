package edu.gatech.GroceryExpress.repository;

import edu.gatech.GroceryExpress.entity.CustomClock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomClockRepository extends JpaRepository<CustomClock, String> {
}


