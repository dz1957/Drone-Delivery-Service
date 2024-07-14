package edu.gatech.GroceryExpress.controller;

import edu.gatech.GroceryExpress.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * REST Controller for handling customer-related requests.
 * This controller provides endpoints for creating new customers
 * and displaying information about existing customers.
 */
@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    /**
     * Creates a new customer with the provided details.
     * 
     * @param account The account identifier for the customer.
     * @param firstName The first name of the customer.
     * @param lastName The last name of the customer.
     * @param phoneNumber The phone number of the customer.
     * @param rating The rating of the customer as an integer.
     * @param credit The credit amount of the customer as an integer.
     * @param x The x-coordinate of the customer's location.
     * @param y The y-coordinate of the customer's location.
     * @return A string message indicating the result of the operation.
     */
    @PostMapping("/makeCustomer")
    public String makeCustomer(@RequestParam String account, @RequestParam String firstName, @RequestParam String lastName,
                               @RequestParam String phoneNumber, @RequestParam int rating, @RequestParam int credit, @RequestParam int x, @RequestParam int y) {
        return customerService.makeCustomer(account, firstName, lastName, phoneNumber, rating, credit, x, y);
    }

    /**
     * Retrieves and displays information about all customers.
     * 
     * @return A string representation of all customers' information.
     */
    @GetMapping("/displayCustomers")
    public String displayCustomers() {
        return customerService.displayCustomers();
    }
}
