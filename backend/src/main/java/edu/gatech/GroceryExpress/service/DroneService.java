package edu.gatech.GroceryExpress.service;

import edu.gatech.GroceryExpress.entity.Drone;
import edu.gatech.GroceryExpress.entity.DronePilot;
import edu.gatech.GroceryExpress.entity.Order;
import edu.gatech.GroceryExpress.entity.Store;
import edu.gatech.GroceryExpress.repository.DroneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static edu.gatech.GroceryExpress.util.Constant.*;

/**
 * Service class for managing drone entities. This class provides methods for creating drones,
 * assigning them to pilots, and handling drone logistics such as travel and fuel management.
 * 
 * @author Team 30
 * @since Dec 1th, 2023
 */

@Service
public class DroneService {
    @Autowired
    private DroneRepository repository;
    @Autowired
    private StoreService storeService;
    @Autowired
    private CustomClockService customClockService;
    @Autowired
    private DronePilotService dronePilotService;
    @Autowired
    private OrderService orderService;

    /**
     * Creates a new drone and assigns it to a store. It checks for the existence of the store
     * and the uniqueness of the drone ID. Also validates the drone's refueling rate.
     *
     * @param storeName     The name of the store to which the drone is to be assigned.
     * @param droneId       The unique identifier for the new drone.
     * @param liftingCapacity        The weight capacity of the drone.
     * @param fuel     The current fuel of the drone.
     * @param fuelCapacity  The fuel capacity of the drone.
     * @param fuelRate      The fuel consumption rate of the drone.
     * @param refuelingRate The rate at which the drone can be refueled.
     * @param speed         The speed of the drone.
     * @return A string indicating the outcome of the creation process, such as an error message or success confirmation.
     */
    public String makeDrone(String storeName, String droneId, int liftingCapacity, int fuel, int fuelCapacity, int fuelRate, int refuelingRate, int speed) {
        TreeMap<String, Store> stores = storeService.getStoresAsTreeMap();
        if (!stores.containsKey(storeName)) {
            System.out.println(ERR_STORE_ID_NOT_EXIST);
            return ERR_STORE_ID_NOT_EXIST;
        } else {
            Store store = stores.get(storeName);
            if (store.getDrones().containsKey(droneId)) {
                System.out.println(ERR_DRONE_ID_EXIST);
                return ERR_DRONE_ID_EXIST;
            } else {
                if (refuelingRate * 10 * customClockService.getMillisPerHour() < fuelCapacity) {
                    System.out.println(ERR_DRONE_REFUELING_RATE_LOW);
                    return ERR_DRONE_REFUELING_RATE_LOW;
                }
                Drone drone = new Drone(droneId, store, liftingCapacity, fuel, fuelCapacity, fuelRate, refuelingRate, speed, store.getXCoordinate(), store.getYCoordinate());
                storeService.addDrone(store, drone);
                System.out.println(OK_CHG_COMPLETE);
                return OK_CHG_COMPLETE;
            }
        }
    }


    /**
     * Retrieves the lifting capacity of a given drone.
     *
     * @param drone The drone whose lifting capacity is to be retrieved.
     * @return The lifting capacity of the drone.
     */
    public int getLiftingCapacity(Drone drone) {
        return drone.getLiftingCapacity();
    }

    /**
     * Adds an order to a drone's list of orders and updates the repository.
     *
     * @param drone The drone to which the order is to be added.
     * @param order The order to be added to the drone's list of orders.
    */
    public void addOrder(Drone drone, Order order) {
        List<Order> orders = drone.getOrders();
        orders.add(order);
        drone.setOrders(orders);
        repository.save(drone);
    }

    /**
     * Retrieves the list of orders assigned to a given drone.
     *
     * @param drone The drone whose orders are to be retrieved.
     * @return A list of orders assigned to the drone.
     */
    public List<Order> getOrders(Drone drone) {
        return drone.getOrders();
    }

    /**
     * Removes an order from a drone's list of orders and updates the repository.
     *
     * @param drone The drone from which the order is to be removed.
     * @param order The order to be removed from the drone's list of orders.
     */
    public void removeOrder(Drone drone, Order order) {
        List<Order> orders = drone.getOrders();
        orders.remove(order);
        drone.setOrders(orders);
        repository.save(drone);
    }

    /**
     * Retrieves the current pilot assigned to a given drone.
     *
     * @param drone The drone whose current pilot is to be retrieved.
     * @return The DronePilot object representing the current pilot of the drone.
     */
    public DronePilot getCurrentPilot(Drone drone) {
        return drone.getCurrentPilot();
    }

    /**
     * Sets the fuel rate for a specified drone belonging to a given store.
     *
     * @param storeName The name of the store the drone belongs to.
     * @param droneId The unique identifier for the drone whose fuel rate is to be set.
     * @param fuelRate The new fuel rate to be set for the drone.
     * @return A string message indicating the result of the operation. It returns
     *         {@code ERR_STORE_ID_NOT_EXIST} if the store name does not exist,
     *         {@code ERR_DRONE_ID_NOT_EXIST} if the drone ID is not found, and
     *         {@code OK_CHG_COMPLETE} if the fuel rate was successfully updated.
     */
    public String setFuelRate(String storeName, String droneId, int fuelRate) {
        TreeMap<String, Store> stores = storeService.getStoresAsTreeMap();
        if (!stores.containsKey(storeName)) {
            return ERR_STORE_ID_NOT_EXIST;
        } else {
            Store store = stores.get(storeName);
            Drone drone = storeService.getDrone(store, droneId);
            if (drone == null) {
                return ERR_DRONE_ID_NOT_EXIST;
            } else {
                drone.setFuelRate(fuelRate);
                repository.save(drone);
                return OK_CHG_COMPLETE;
            }
        }
    }

    /**
     * Sets the refueling rate for a specified drone belonging to a given store.
     *
     * @param storeName The name of the store the drone belongs to.
     * @param droneId The unique identifier for the drone whose refueling rate is to be set.
     * @param newRefuelingRate The new refueling rate to be set for the drone.
     * @return A string message indicating the result of the operation. It returns
     *         {@code ERR_STORE_ID_NOT_EXIST} if the store name does not exist,
     *         {@code ERR_DRONE_ID_NOT_EXIST} if the drone ID is not found, and
     *         {@code OK_CHG_COMPLETE} if the refueling rate was successfully updated.
     */
    public String setRefuelingRate(String storeName, String droneId, int newRefuelingRate) {
        TreeMap<String, Store> stores = storeService.getStoresAsTreeMap();
        if (!stores.containsKey(storeName)) {
            return ERR_STORE_ID_NOT_EXIST;
        } else {
            Store store = stores.get(storeName);
            Drone drone = storeService.getDrone(store, droneId);
            if (drone == null) {
                return ERR_DRONE_ID_NOT_EXIST;
            } else {
                drone.setRefuelingRate(newRefuelingRate);
                repository.save(drone);
                return OK_CHG_COMPLETE;
            }
        }
    }
  
     /**
     * Calculates the fuel required for a drone to travel a given distance based on its fuel rate.
     *
     * @param drone    The drone for which the fuel requirement is to be calculated.
     * @param distance The distance the drone needs to travel.
     * @return The amount of fuel required for the drone to travel the specified distance.
     */
    public int calculateFuelRequired(Drone drone, int distance) {
        return distance * drone.getFuelRate();
    }

    /**
     * Refuels a drone with the required amount of fuel, considering the current time and power level.
     * This method also advances the custom clock service by the duration of the refueling process.
     *
     * @param drone        The drone to be refueled.
     * @param fuelRequired The amount of fuel required to refuel the drone.
     */
    private void refuel(Drone drone, int fuelRequired) {
        System.out.println("Drone " + " is refueling");
        long currentTimeMillis = customClockService.currentTimeMillis();
        long nextShift = customClockService.getNextDayNightCutOffMillis();
        int currentRate = customClockService.getPowerLevel() * drone.getRefuelingRate();
        long refuelDuration = (fuelRequired) / currentRate;
        if (currentTimeMillis + refuelDuration > nextShift) {
            long refuelAmount = (nextShift - currentTimeMillis) * currentRate;
            int nextRate = 0;
            if (customClockService.isDayTime()) {
                nextRate = currentRate / (customClockService.getDayTimePowerLevel() / customClockService.getNightTimePowerLevel());
            } else {
                nextRate = currentRate * (customClockService.getDayTimePowerLevel() / customClockService.getNightTimePowerLevel());
            }
            long refuelDuration2 = (fuelRequired - refuelAmount) / nextRate;
            refuelDuration = refuelDuration + refuelDuration2;
        }
        customClockService.advanceTime(refuelDuration);
        drone.setFuel(drone.getFuel() + fuelRequired);
    }

    /**
     * Calculates the duration of a trip for a drone to travel a given distance based on its speed.
     * The duration is calculated in milliseconds.
     *
     * @param drone    The drone for which the trip duration is to be calculated.
     * @param distance The distance the drone will travel.
     * @return The duration of the trip in milliseconds.
     */
    private long calculateTripDurationMillis(Drone drone, int distance) {
        return distance / drone.getSpeed();
    }

    /**
     * Calculates the current total weight of the orders carried by a drone.
     *
     * @param drone The drone whose total order weight is to be calculated.
     * @return The total weight of all orders carried by the drone.
     */
    public int getWeight(Drone drone) {
        List<Order> orders = drone.getOrders();
        int weight = 0;
        for (Order order : orders) {
            weight += orderService.getWeight(order);
        }
        return weight;
    }

    /**
     * Checks if the drone can travel to its associated store based on its current fuel capacity
     * and fuel consumption rate.
     *
     * @param drone The drone to check for valid travel distance.
     * @return {@code true} if the drone can travel to the store with its current fuel, {@code false} otherwise.
     */
    public boolean checkValidTravelDistance(Drone drone) {
        int travelDistance = DistanceService.getDistance(drone.getXCoordinate(), drone.getYCoordinate(), storeService.getXCoordinate(drone.getStore()), storeService.getYCoordinate(drone.getStore()));
        int maxDistance = drone.getFuelCapacity() / drone.getFuelRate();
        return travelDistance <= maxDistance;

    }

    /**
     * Moves the drone to new coordinates, adjusting its fuel based on the distance traveled.
     * This method also takes care of refueling the drone if necessary.
     *
     * @param drone      The drone to be moved.
     * @param xCoordinate The x-coordinate to which the drone is moving.
     * @param yCoordinate The y-coordinate to which the drone is moving.
     */
    public void travel(Drone drone, int xCoordinate, int yCoordinate) {
        System.out.println("Drone " + " is traveling to (" + xCoordinate + "," + yCoordinate + ")");
        int distanceToNextAddress = DistanceService.getDistance(drone.getXCoordinate(), drone.getYCoordinate(), xCoordinate, yCoordinate);
        int totalFuel = calculateFuelRequired(drone, distanceToNextAddress);
        int fuelRequired = totalFuel - drone.getFuel();
        if (fuelRequired > 0) {
            refuel(drone, fuelRequired);
        }

        long tripDurationMillis = calculateTripDurationMillis(drone, distanceToNextAddress);

        drone.setFuel(drone.getFuel() - totalFuel);
        drone.setXCoordinate(xCoordinate);
        drone.setYCoordinate(yCoordinate);

        customClockService.advanceTime(tripDurationMillis);

    }

    /**
     * Assigns a drone pilot to fly a specific drone at a particular store.
     * This method validates the existence of the store, drone, and pilot before assignment.
     *
     * @param storeName The name of the store where the drone is located.
     * @param droneId   The ID of the drone to be flown.
     * @param pilotId   The ID of the pilot who will fly the drone.
     * @return A string indicating the outcome of the drone flying assignment.
     */
    public String flyDrone(String storeName, String droneId, String pilotId) {
        TreeMap<String, Store> stores = storeService.getStoresAsTreeMap();
        TreeMap<String, DronePilot> pilots = dronePilotService.getDronePilotsAsTreeMap();
        if (!stores.containsKey(storeName)) {
            System.out.println(ERR_STORE_ID_NOT_EXIST);
            return ERR_STORE_ID_NOT_EXIST;
        } else {
            Store store = stores.get(storeName);
            if (!store.getDrones().containsKey(droneId)) {
                System.out.println(ERR_DRONE_ID_NOT_EXIST);
                return ERR_DRONE_ID_NOT_EXIST;
            } else if (!pilots.containsKey(pilotId)) {
                System.out.println(ERR_PILOT_ID_NOT_EXIST);
                return ERR_PILOT_ID_NOT_EXIST;
            } else {
                Drone drone = store.getDrones().get(droneId);
                DronePilot pilot = pilots.get(pilotId);

                if (pilot.getDrone() != null) {
                    pilot.getDrone().setCurrentPilot(null);
                }
                if (drone.getCurrentPilot() != null) {
                    drone.getCurrentPilot().setDrone(null);

                }
                drone.setCurrentPilot(pilot);
                repository.save(drone);
                dronePilotService.setDrone(pilot, drone);
                System.out.println(OK_CHG_COMPLETE);
                return OK_CHG_COMPLETE;
            }
        }
    }


    /**
     * Displays information for all drones in a given store.
     * The information includes drone ID, capacity, number of orders, and other relevant details.
     *
     * @param storeName The name of the store whose drones are to be displayed.
     * @return A string representation of all drones' information in the specified store.
     */
    public String displayDrones(String storeName) {
        TreeMap<String, Store> stores = storeService.getStoresAsTreeMap();
        if (!stores.containsKey(storeName)) {
            System.out.println(ERR_STORE_ID_NOT_EXIST);
            return ERR_STORE_ID_NOT_EXIST;
        } else {
            StringBuilder sb = new StringBuilder();
            Store store = stores.get(storeName);
            for (Map.Entry<String, Drone> entry : store.getDrones().entrySet()) {
                sb.append(display(entry.getValue())).append('\n');
            }
            System.out.println(OK_DISPLAY_COMPLETE);
            sb.append(OK_DISPLAY_COMPLETE);
            return sb.toString();

        }
    }

    /**
     * Formats and displays information for a single drone.
     * The information includes drone ID, total capacity, number of orders, and other details.
     *
     * @param drone The drone whose information is to be displayed.
     * @return A string representation of the drone's information.
     */
    public String display(Drone drone) {
        String droneInfo = "droneID:" + drone.getId() + ",total_cap:" + drone.getLiftingCapacity() + ",num_orders:" + drone.getOrders().size()
                + ",remaining_cap:" + (drone.getLiftingCapacity() - getWeight(drone)) + ",fuel:" + drone.getFuel() + ",address:(" + drone.getXCoordinate() + "," + drone.getYCoordinate() + ")"
                + ",fuel_capacity:" + drone.getFuelCapacity() + ",fuel_rate:" + drone.getFuelRate() + ",refueling_rate:" + drone.getRefuelingRate() + ",speed:" + drone.getSpeed();
        if (drone.getCurrentPilot() != null) {
            droneInfo += ",flown_by:" + drone.getCurrentPilot().getFirstName() + "_" + drone.getCurrentPilot().getLastName();
        }
        System.out.println(droneInfo);
        return droneInfo;
    }

    /**
     * Retrieves all drones from the repository and returns them as a TreeMap sorted alphabetically by drone ID.
     * This is useful for displaying drones in a sorted manner.
     *
     * @return A TreeMap where each key is a drone's ID and the value is the Drone object.
     */
    public TreeMap<String, Drone> getDronesAsTreeMap() {
        List<Drone> droneList = repository.findAll();
        TreeMap<String, Drone> drones = new TreeMap<>();
        for (Drone drone : droneList) {
            drones.put(drone.getId(), drone);
        }
        return drones;
    }
}
