package edu.gatech.GroceryExpress.repository;

import edu.gatech.GroceryExpress.entity.Line;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LineRepository extends JpaRepository<Line, String> {
}


