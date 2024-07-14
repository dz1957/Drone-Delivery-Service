package edu.gatech.GroceryExpress.controller;

import edu.gatech.GroceryExpress.service.CustomClockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The {@code CustomClockController} class is a Spring REST Controller
 * that handles HTTP GET requests related to the custom clock functionality.
 * It delegates the business logic to the {@code CustomClockService}.
 */

@RestController
public class CustomClockController {
    @Autowired
    private CustomClockService customClockService;

    /**
     * Handles the GET request to display the current time.
     * 
     * This method delegates the call to {@code CustomClockService} to
     * retrieve and return the formatted current time as a string.
     *
     * @return A string representing the current time formatted as per
     *         the implementation in {@code CustomClockService}.
     */
    @GetMapping("/displayTime")
    public String displayTime() {
        return customClockService.displayTime();
    }
}
