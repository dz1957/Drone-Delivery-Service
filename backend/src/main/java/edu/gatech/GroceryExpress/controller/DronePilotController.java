package edu.gatech.GroceryExpress.controller;

import edu.gatech.GroceryExpress.service.DronePilotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * Controller for handling HTTP requests related to drone pilots.
 * This controller provides endpoints for registering new drone pilots
 * and for displaying information about registered pilots.
 */
@RestController
public class DronePilotController {
    @Autowired
    private DronePilotService dronePilotService;

    /**
     * Registers a new drone pilot with the provided details.
     * 
     * @param pilotAccount The account identifier for the pilot.
     * @param firstName The first name of the pilot.
     * @param lastName The last name of the pilot.
     * @param phoneNumber The contact phone number of the pilot.
     * @param taxId The tax identification number of the pilot.
     * @param licenseId The drone pilot's license identifier.
     * @param expLevel The experience level of the pilot as an integer.
     * @return A string message indicating the result of the pilot registration operation.
     */
    @PostMapping("/makePilot")
    public String makePilot(@RequestParam String pilotAccount, @RequestParam String firstName, @RequestParam String lastName,
                            @RequestParam String phoneNumber, @RequestParam String taxId, @RequestParam String licenseId, @RequestParam int expLevel) {
        return dronePilotService.makePilot(pilotAccount, firstName, lastName, phoneNumber, taxId, licenseId, expLevel);
    }

    /**
     * Retrieves and displays information about all registered drone pilots.
     * 
     * @return A string representation of all registered pilots' information.
     */
    @GetMapping("/displayPilots")
    public String displayPilots() {
        return dronePilotService.displayPilots();
    }
}
