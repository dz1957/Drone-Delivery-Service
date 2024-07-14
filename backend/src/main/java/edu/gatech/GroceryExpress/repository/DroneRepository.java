package edu.gatech.GroceryExpress.repository;

import edu.gatech.GroceryExpress.entity.Drone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneRepository extends JpaRepository<Drone, String> {
}


