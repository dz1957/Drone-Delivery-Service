package edu.gatech.GroceryExpress.service;

import edu.gatech.GroceryExpress.entity.*;
import edu.gatech.GroceryExpress.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.TreeMap;

import static edu.gatech.GroceryExpress.util.Constant.*;

/**
 * Service class for managing item entities. This class provides methods for selling items,
 * handling item requests, and displaying item information.
 * 
 * @author Team 30
 * @since Dec 1th, 2023
 */

@Service
public class ItemService {
    @Autowired
    private ItemRepository repository;
    @Autowired
    private StoreService storeService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private DroneService droneService;
    @Autowired
    private LineService lineService;

    /**
     * Creates a new item and adds it to a store. This method checks for the existence of the store
     * and uniqueness of the item name within the store.
     *
     * @param storeName The name of the store where the item is to be sold.
     * @param itemName  The name of the item to be sold.
     * @param weight    The weight of the item.
     * @return A string indicating the outcome of the selling process, such as an error message or success confirmation.
     */
    public String sellItem(String storeName, String itemName, int weight) {
        if (!storeService.getStoresAsTreeMap().containsKey(storeName)) {
            System.out.println(ERR_STORE_ID_NOT_EXIST);
            return ERR_STORE_ID_NOT_EXIST;
        } else {
            Store store = storeService.getStoresAsTreeMap().get(storeName);
            Item newItem = new Item(itemName, weight);
            repository.save(newItem);
            boolean res = storeService.addItem(store, newItem);
            if (res) {
                System.out.println(OK_CHG_COMPLETE);
                return OK_CHG_COMPLETE;
            } else {
                System.out.println(ERR_ITEM_ID_EXIST);
                return ERR_ITEM_ID_EXIST;
            }
        }
    }

    /**
     * Retrieves the name of a given item.
     *
     * @param item The item whose name is to be retrieved.
     * @return The name of the item.
     */
    public String getName(Item item) {
        return item.getName();
    }

    /**
     * Retrieves the weight of a given item.
     *
     * @param item The item whose weight is to be retrieved.
     * @return The weight of the item.
     */
    public int getWeight(Item item) {
        return item.getWeight();
    }

    /**
     * Processes an item request for a specific order. Validates the existence of the store, order, and item.
     * Checks whether the customer has enough credit and the drone has enough capacity to carry the item.
     *
     * @param storeName The name of the store where the item is requested.
     * @param orderID   The ID of the order to which the item is to be added.
     * @param itemId    The ID of the requested item.
     * @param quantity  The quantity of the item requested.
     * @param unitPrice The unit price of the item.
     * @return A string indicating the result of the item request, such as an error message or success confirmation.
     */
    public String requestItem(String storeName, String orderID, String itemId, int quantity, int unitPrice) {
        TreeMap<String, Store> stores = storeService.getStoresAsTreeMap();
        if (stores.containsKey(storeName)) {
            Store store = stores.get(storeName);

            if (storeService.containOrder(store, orderID)) {
                Order order = storeService.getOrder(store, orderID);

                if (storeService.containItem(store, itemId)) {
                    Item item = storeService.getItem(store, itemId);

                    if (!orderService.containItem(order, itemId)) {
                        Customer customer = orderService.getCustomer(order);
                        Drone drone = orderService.getDrone(order);
                        int totalWeight = quantity * item.getWeight();
                        int totalPrice = quantity * unitPrice;

                        if (totalPrice > (customerService.getCredit(customer) - customerService.getOrdersCost(customer))) {
                            System.out.println(ERR_CUSTOMER_CREDIT_LOW_ITEM);
                            return ERR_CUSTOMER_CREDIT_LOW_ITEM;
                        } else if (totalWeight > (droneService.getLiftingCapacity(drone) - droneService.getWeight(drone))) {
                            System.out.println(ERR_DRONE_CAPACITY_LOW);
                            return ERR_DRONE_CAPACITY_LOW;
                        } else {
                            Line line = lineService.makeLine(item, quantity, unitPrice);
                            orderService.addItem(order, itemId);
                            orderService.addLine(order, itemId, line);
                            System.out.println(OK_CHG_COMPLETE);
                            return OK_CHG_COMPLETE;
                        }
                    } else {
                        System.out.println(ERR_ITEM_EXIST);
                        return ERR_ITEM_EXIST;
                    }
                } else {
                    System.out.println(ERR_ITEM_ID_NOT_EXIST);
                    return ERR_ITEM_ID_NOT_EXIST;
                }
            } else {
                System.out.println(ERR_ORDER_ID_NOT_EXIST);
                return ERR_ORDER_ID_NOT_EXIST;
            }
        } else {
            System.out.println(ERR_STORE_ID_NOT_EXIST);
            return ERR_STORE_ID_NOT_EXIST;
        }
    }


    /**
     * Displays information about all items in a given store.
     * The information includes item name, weight, and other relevant details.
     *
     * @param storeName The name of the store whose items are to be displayed.
     * @return A string representation of all items' information in the specified store.
     */
    public String displayItems(String storeName) {
        if (!storeService.getStoresAsTreeMap().containsKey(storeName)) {
            System.out.println(ERR_STORE_ID_NOT_EXIST);
            return ERR_STORE_ID_NOT_EXIST;
        } else {
            StringBuilder sb = new StringBuilder();
            Store store = storeService.getStoresAsTreeMap().get(storeName);
            sb.append(storeService.displayItems(store));
            System.out.println(OK_DISPLAY_COMPLETE);
            sb.append(OK_DISPLAY_COMPLETE);
            return sb.toString();
        }
    }

    /**
     * Retrieves all items from the repository and returns them as a TreeMap sorted alphabetically by item name.
     * This is useful for displaying items in a sorted manner.
     *
     * @return A TreeMap where each key is an item's name and the value is the Item object.
     */
    public TreeMap<String, Item> getItemsAsTreeMap() {
        List<Item> itemList = repository.findAll();
        TreeMap<String, Item> items = new TreeMap<>();
        for (Item item : itemList) {
            items.put(item.getName(), item);
        }
        return items;
    }
}
