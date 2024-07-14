package edu.gatech.GroceryExpress.controller;

import edu.gatech.GroceryExpress.service.LineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller for managing lines within the application.
 * This controller is intended to provide endpoints for operations related to lines,
 * such as creating, updating, and displaying line information.
 * 
 * Methods should be added to this controller to expose such functionality via RESTful endpoints.
 */
@RestController
public class LineController {
    @Autowired
    private LineService lineService;
}
