package edu.gatech.GroceryExpress.service;

import edu.gatech.GroceryExpress.entity.Drone;
import edu.gatech.GroceryExpress.entity.Item;
import edu.gatech.GroceryExpress.entity.Order;
import edu.gatech.GroceryExpress.entity.Store;
import edu.gatech.GroceryExpress.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static edu.gatech.GroceryExpress.util.Constant.*;

/**
 * Service class for managing store entities. This class provides methods for creating stores,
 * managing store inventory, orders, and other related operations.
 * 
 * @author Team 30
 * @since Dec 1th, 2023
 */
@Service
public class StoreService {
    @Autowired
    StoreRepository storeRepository;

    /**
     * Creates a new store with the specified name and location, then saves it in the repository.
     *
     * @param storeName The name of the new store.
     * @param initialRevenue The initial revenue of the store.
     * @param x The x-coordinate of the store's location.
     * @param y The y-coordinate of the store's location.
     * @return A string indicating the outcome of the store creation process.
     */
    public String makeStore(String storeName, int initialRevenue, int x, int y) {
        if (getStoresAsTreeMap().containsKey(storeName)) {
            System.out.println(ERR_STORE_ID_EXIST);
            return ERR_STORE_ID_EXIST;
        } else {
            Store newStore = new Store(storeName, initialRevenue, x, y);
            storeRepository.save(newStore);
            System.out.println(OK_CHG_COMPLETE);
            return OK_CHG_COMPLETE;
        }
    }

    /**
     * Increases the revenue of a store by a specified amount.
     *
     * @param store       The store whose revenue is to be increased.
     * @param orderRevenue The revenue amount to be added.
     */
    public void addRevenue(Store store, int orderRevenue) {
        store.setRevenue(store.getRevenue() + orderRevenue);
    }

    /**
     * Adds an item to a store's inventory if it's not already present.
     *
     * @param store The store to which the item is to be added.
     * @param item  The item to be added to the store's inventory.
     * @return {@code true} if the item was successfully added, {@code false} if it already exists in the store.
     */
    public boolean addItem(Store store, Item item) {
        if (containItem(store, item.getName())) {
            return false;
        }
        store.getItems().put(item.getName(), item);
        storeRepository.save(store);
        return true;
    }

    /**
     * Retrieves an item from a store's inventory by its ID.
     *
     * @param store  The store from which to retrieve the item.
     * @param itemId The ID of the item to retrieve.
     * @return The item corresponding to the given ID, or {@code null} if not found.
     */
    public Item getItem(Store store, String itemId) {
        return store.getItems().get(itemId);
    }

    /**
     * Checks if a store contains a specific item.
     *
     * @param store  The store to check.
     * @param itemId The ID of the item to check for.
     * @return {@code true} if the store contains the item, {@code false} otherwise.
     */
    public boolean containItem(Store store, String itemId) {
        return store.getItems().containsKey(itemId);
    }

    /**
     * Checks if a store contains a specific drone.
     *
     * @param store   The store to check.
     * @param droneId The ID of the drone to check for.
     * @return {@code true} if the store contains the drone, {@code false} otherwise.
     */
    public boolean containDrone(Store store, String droneId) {
        return store.getDrones().containsKey(droneId);
    }

    /**
     * Adds a drone to a store's fleet and saves the updated store information.
     *
     * @param store The store to which the drone is to be added.
     * @param drone The drone to add to the store's fleet.
     */
    public void addDrone(Store store, Drone drone) {
        store.getDrones().put(drone.getId(), drone);
        storeRepository.save(store);
    }

    /**
     * Retrieves a drone from a store's fleet by its ID.
     *
     * @param store   The store from which to retrieve the drone.
     * @param droneId The ID of the drone to retrieve.
     * @return The drone corresponding to the given ID, or {@code null} if not found.
     */
    public Drone getDrone(Store store, String droneId) {
        return store.getDrones().get(droneId);
    }

    /**
     * Retrieves a map of all drones in a store.
     *
     * @param store The store from which to retrieve the drones.
     * @return A TreeMap of drones sorted by their IDs.
     */
    public Map<String, Drone> getDrones(Store store) {
        return store.getDrones();
    }

    /**
     * Adds an order to a store and saves the updated store information.
     *
     * @param store   The store to which the order is to be added.
     * @param orderId The ID of the order to add.
     * @param order   The order to be added.
     */
    public void addOrder(Store store, String orderId, Order order) {
        store.getOrders().put(orderId, order);
        storeRepository.save(store);
    }

    /**
     * Retrieves an order from a store by its ID.
     *
     * @param store   The store from which to retrieve the order.
     * @param orderId The ID of the order to retrieve.
     * @return The order corresponding to the given ID, or {@code null} if not found.
     */
    public Order getOrder(Store store, String orderId) {
        return store.getOrders().get(orderId);
    }

    /**
     * Removes an order from a store and saves the updated store information.
     *
     * @param store   The store from which the order is to be removed.
     * @param orderId The ID of the order to remove.
     */
    public void removeOrder(Store store, String orderId) {
        store.getOrders().remove(orderId);
        storeRepository.save(store);
    }

    /**
     * Checks if a store contains a specific order.
     *
     * @param store   The store to check.
     * @param orderId The ID of the order to check for.
     * @return {@code true} if the store contains the order, {@code false} otherwise.
     */
    public boolean containOrder(Store store, String orderId) {
        return store.getOrders().containsKey(orderId);
    }

    /**
     * Retrieves a map of all orders in a store.
     *
     * @param store The store from which to retrieve the orders.
     * @return A TreeMap of orders sorted by their IDs.
     */
    public TreeMap<String, Order> getOrders(Store store) {
        return new TreeMap<String, Order>(store.getOrders());
    }

    /**
     * Adds a completed order to the store's records and saves the updated store information.
     *
     * @param store   The store to which the completed order is to be added.
     * @param orderId The ID of the completed order to add.
     * @param order   The completed order to be added.
     */
    public void addCompletedOrders(Store store, String orderId, Order order) {
        store.getCompletedOrders().put(orderId, order);
        storeRepository.save(store);
    }

    /**
     * Checks if a store contains a specific completed order.
     *
     * @param store   The store to check.
     * @param orderId The ID of the completed order to check for.
     * @return {@code true} if the store contains the completed order, {@code false} otherwise.
     */
    public boolean containCompletedOrder(Store store, String orderId) {
        return store.getCompletedOrders().containsKey(orderId);
    }

    /**
     * Retrieves a completed order from a store by its ID.
     *
     * @param store   The store from which to retrieve the completed order.
     * @param orderId The ID of the completed order to retrieve.
     * @return The completed order corresponding to the given ID, or {@code null} if not found.
     */
    public Order getCompletedOrder(Store store, String orderId) {
        return store.getCompletedOrders().get(orderId);
    }

    /**
     * Retrieves a map of all completed orders in a store.
     *
     * @param store The store from which to retrieve the completed orders.
     * @return A TreeMap of completed orders sorted by their IDs.
     */
    public TreeMap<String, Order> getCompletedOrders(Store store) {
        return (TreeMap<String, Order>) store.getCompletedOrders();
    }

    /**
     * Increases the transfer count of a store by one.
     *
     * @param store The store whose transfer count is to be increased.
     */
    public void addTransferCount(Store store) {
        store.setTransferCount(store.getTransferCount() + 1);
    }

    /**
     * Retrieves the current transfer count of a store.
     *
     * @param store The store from which to retrieve the transfer count.
     * @return The current transfer count of the store.
     */
    public int getTransferCount(Store store) {
        return store.getTransferCount() + 1;
    }

    /**
     * Increases the overload value of a store by a specified amount.
     *
     * @param store         The store whose overload value is to be increased.
     * @param droneOverload The amount by which to increase the overload value.
     */
    public void addOverload(Store store, int droneOverload) {
        store.setOverload(store.getOverload() + droneOverload);
    }

    /**
     * Retrieves the current overload value of a store.
     *
     * @param store The store from which to retrieve the overload value.
     * @return The current overload value of the store.
     */
    public int getOverload(Store store) {
        return store.getOverload();
    }

    /**
     * Retrieves the X-coordinate of the store's location.
     *
     * @param store The store from which to retrieve the X-coordinate.
     * @return The X-coordinate of the store's location.
     */
    public int getXCoordinate(Store store) {
        return store.getXCoordinate();
    }

    /**
     * Sets the X-coordinate of the store's location.
     *
     * @param store       The store whose X-coordinate is to be set.
     * @param xCoordinate The X-coordinate to set for the store's location.
     */
    public void setXCoordinate(Store store, int xCoordinate) {
        store.setXCoordinate(xCoordinate);
    }

    /**
     * Retrieves the Y-coordinate of the store's location.
     *
     * @param store The store from which to retrieve the Y-coordinate.
     * @return The Y-coordinate of the store's location.
     */
    public int getYCoordinate(Store store) {
        return store.getYCoordinate();
    }

    /**
     * Sets the Y-coordinate of the store's location.
     *
     * @param store       The store whose Y-coordinate is to be set.
     * @param yCoordinate The Y-coordinate to set for the store's location.
     */
    public void setYCoordinate(Store store, int yCoordinate) {
        store.setYCoordinate(yCoordinate);
    }


    /**
     * Generates a formatted string containing information about all stores in alphabetical order.
     *
     * @return A string containing information about all stores, each separated by a newline.
     */
    public String displayStores() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Store> entry :
                getStoresAsTreeMap().entrySet()) {
            sb.append(display(entry.getValue())).append('\n');
        }
        System.out.println(OK_DISPLAY_COMPLETE);
        sb.append(OK_DISPLAY_COMPLETE);
        return sb.toString();
    }

    /**
     * Generates a formatted string containing efficiency-related information about all stores.
     *
     * @return A string containing efficiency information about all stores, each separated by a newline.
     */
    public String displayEfficiency() {
        TreeMap<String, Store> stores = getStoresAsTreeMap();
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Store> entry :
                stores.entrySet()) {
            Store store = entry.getValue();
            int purOrdAmt = store.getCompletedOrders().size();
            int olOrdAmt = store.getOverload();
            String str = "name:" + store.getName()
                    + ",purchases:" + purOrdAmt
                    + ",overloads:" + olOrdAmt
                    + ",transfers:" + store.getTransferCount();
            System.out.println(str);
            sb.append(str).append('\n');
        }
        System.out.println(OK_DISPLAY_COMPLETE);
        sb.append(OK_DISPLAY_COMPLETE);
        return sb.toString();
    }

    /**
     * Generates a formatted string containing information about items in a specific store.
     *
     * @param store The store for which to display item information.
     * @return A string containing information about items in the store, each separated by a newline.
     */
    public String displayItems(Store store) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Item> entry : store.getItems().entrySet()) {
            Item item = entry.getValue();
            System.out.println(item.getName() + "," + item.getWeight());
            sb.append(item.getName()).append(",").append(item.getWeight()).append('\n');
        }
        return sb.toString();
    }

    /**
     * Generates a formatted string containing general information about a store.
     *
     * @param store The store for which to display general information.
     * @return A string containing general information about the store.
     */
    public String display(Store store) {
        String str;
        str = "name:" + store.getName() + ",revenue:" + store.getRevenue() + ",address:(" + store.getXCoordinate() + "," + store.getYCoordinate() + ")";
        System.out.println(str);
        return str;
    }


    /**
     * Retrieves a TreeMap of all stores sorted alphabetically by name.
     *
     * @return A TreeMap of all stores, sorted by their names.
     */
    public TreeMap<String, Store> getStoresAsTreeMap() {
        List<Store> storeList = storeRepository.findAll();
        TreeMap<String, Store> stores = new TreeMap<>();
        for (Store store : storeList) {
            stores.put(store.getName(), store);
        }
        return stores;
    }

    /**
     * Retrieves a TreeMap of completed orders for a specific store.
     *
     * @param store The store for which to retrieve completed orders.
     * @return A TreeMap of completed orders for the specified store, sorted by their IDs.
    */
    public TreeMap<String, Order> getStoreCompletedOrdersAsTreeMap(Store store) {
        return new TreeMap<>(store.getCompletedOrders());
    }
}
