package edu.gatech.GroceryExpress;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = "*")
public class DeliveryService {
    public static void main(String[] args) {
        SpringApplication.run(DeliveryService.class, args);
    }
}
