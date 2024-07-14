package edu.gatech.GroceryExpress.controller;

import edu.gatech.GroceryExpress.service.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Controller for handling requests related to drone operations within a store.
 * Provides endpoints for creating drones, flying drones, displaying drone information,
 * and setting drone-specific parameters like fuel rate and refueling rate.
 */
@RestController
public class DroneController {
    @Autowired
    private DroneService droneService;

    /**
     * Creates a new drone and associates it with a store.
     *
     * @param storeName The name of the store the drone is associated with.
     * @param droneId The unique identifier for the new drone.
     * @param liftingCapacity The lifting capacity of the drone in units.
     * @param fuel The initial amount of fuel in the drone.
     * @param fuelCapacity The maximum fuel capacity of the drone.
     * @param fuelRate The rate at which the drone consumes fuel.
     * @param refuelingRate The rate at which the drone can be refueled.
     * @param speed The speed of the drone in units per time period.
     * @return A string message indicating the result of the drone creation operation.
     */
    @PostMapping("/makeDrone")
    public String makeDrone(@RequestParam String storeName, @RequestParam String droneId, @RequestParam int liftingCapacity, @RequestParam int fuel, @RequestParam int fuelCapacity, @RequestParam int fuelRate, @RequestParam int refuelingRate, @RequestParam int speed) {
        return droneService.makeDrone(storeName, droneId, liftingCapacity, fuel, fuelCapacity, fuelRate, refuelingRate, speed);
    }

    /**
     * Assigns a pilot to fly a drone at a specific store.
     *
     * @param storeName The name of the store where the drone is located.
     * @param droneId The unique identifier of the drone to be flown.
     * @param pilotId The identifier of the pilot who will fly the drone.
     * @return A string message indicating the result of the operation.
     */
    @PutMapping("/flyDrone")
    public String flyDrone(@RequestParam String storeName, @RequestParam String droneId, @RequestParam String pilotId) {
        return droneService.flyDrone(storeName, droneId, pilotId);
    }

    /**
     * Displays information about all drones associated with a specific store.
     *
     * @param storeName The name of the store whose drones' information will be displayed.
     * @return A string representation of the drones' information.
     */
    @GetMapping("/displayDrones")
    public String displayDrones(@RequestParam String storeName) {
        return droneService.displayDrones(storeName);
    }

    /**
     * Sets the refueling rate for a specified drone at a given store.
     *
     * @param storeName The name of the store the drone is associated with.
     * @param droneId The unique identifier of the drone whose refueling rate is to be set.
     * @param refuelingRate The new refueling rate for the drone.
     * @return A string message indicating the result of the operation.
     */
    @PutMapping("/setRefuelingRate")
    public String setRefuelingRate(@RequestParam String storeName, @RequestParam String droneId, @RequestParam int refuelingRate) {
        return droneService.setRefuelingRate(storeName, droneId, refuelingRate);
    }

    /**
     * Sets the fuel consumption rate for a specified drone at a given store.
     *
     * @param storeName The name of the store the drone is associated with.
     * @param droneId The unique identifier of the drone whose fuel rate is to be set.
     * @param fuelRate The new fuel consumption rate for the drone.
     * @return A string message indicating the result of the operation.
     */
    @PutMapping("/setFuelRate")
    public String setFuelRate(@RequestParam String storeName, @RequestParam String droneId, @RequestParam int fuelRate) {
        return droneService.setFuelRate(storeName, droneId, fuelRate);
    }


}
