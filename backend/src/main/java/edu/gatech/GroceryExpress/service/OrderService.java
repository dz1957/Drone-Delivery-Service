package edu.gatech.GroceryExpress.service;

import edu.gatech.GroceryExpress.entity.*;
import edu.gatech.GroceryExpress.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import static edu.gatech.GroceryExpress.util.Constant.*;

/**
 * Service class for managing order entities. This class provides methods for creating, modifying, 
 * and displaying orders, as well as handling order-related transactions.
 * 
 * @author Team 30
 * @since Dec 1th, 2023
 */

@Service
public class OrderService {
    @Autowired
    private OrderRepository repository;
    @Autowired
    private LineService lineService;
    @Autowired
    private StoreService storeService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomClockService customClockService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private DroneService droneService;
    @Autowired
    private DronePilotService dronePilotService;

    /**
     * Creates a new order for a customer using a specific drone from a store. Validates the existence
     * of the store, drone, and customer before creating the order.
     *
     * @param storeName        The name of the store where the order is initiated.
     * @param orderId          The unique identifier for the new order.
     * @param droneId          The identifier of the drone to be used for the order.
     * @param customerAccount  The account identifier of the customer placing the order.
     * @return A string indicating the outcome of the order creation process.
     */
    public String startOrder(String storeName, String orderId, String droneId, String customerAccount) {
        TreeMap<String, Store> stores = storeService.getStoresAsTreeMap();
        TreeMap<String, Customer> customers = customerService.getCustomerAsTreeMap();
        if (stores.containsKey(storeName)) {
            Store store = stores.get(storeName);
            if (!storeService.containOrder(store, orderId)) {
                if (storeService.containDrone(store, droneId)) {
                    Drone drone = storeService.getDrone(store, droneId);
                    if (customers.containsKey(customerAccount)) {
                        Customer customer = customers.get(customerAccount);
                        Order order = new Order(orderId, store, drone, customer, customClockService.currentTimeMillis());
                        repository.save(order);
                        customerService.addOrder(customer, order);
                        droneService.addOrder(drone, order);
                        storeService.addOrder(store, orderId, order);
                        System.out.println(OK_CHG_COMPLETE);
                        return OK_CHG_COMPLETE;
                    } else {
                        System.out.println(ERR_CUSTOMER_ID_NOT_EXIST);
                        return ERR_CUSTOMER_ID_NOT_EXIST;
                    }
                } else {
                    System.out.println(ERR_DRONE_ID_NOT_EXIST);
                    return ERR_DRONE_ID_NOT_EXIST;
                }
            } else {
                System.out.println(ERR_ORDER_ID_EXIST);
                return ERR_ORDER_ID_EXIST;
            }
        } else {
            System.out.println(ERR_STORE_ID_NOT_EXIST);
            return ERR_STORE_ID_NOT_EXIST;
        }
    }

    /**
     * Retrieves the unique identifier of a given order.
     *
     * @param order The order from which to retrieve the ID.
     * @return The unique identifier of the order.
     */
    public String getOrderId(Order order) {
        return order.getId();
    }

    /**
     * Retrieves the drone assigned to a given order.
     *
     * @param order The order from which to retrieve the drone.
     * @return The drone assigned to the order.
     */
    public Drone getDrone(Order order) {
        return order.getDrone();
    }

    /**
     * Retrieves the customer who placed a given order.
     *
     * @param order The order from which to retrieve the customer.
     * @return The customer who placed the order.
     */
    public Customer getCustomer(Order order) {
        return order.getCustomer();
    }

    public Boolean getOrderReturned(Order order) {
        return order.getOrderReturned();
    }
  
    /**
     * Adds a line item to an order and updates the repository.
     *
     * @param order  The order to which the line is to be added.
     * @param itemId The identifier of the item in the line.
     * @param line   The line item to be added to the order.
     */
    public void addLine(Order order, String itemId, Line line) {
        Map<String, Line> lines = order.getLines();
        lines.put(itemId, line);
        order.setLines(lines);
        repository.save(order);
    }

    /**
     * Adds an item identifier to an order's item set and updates the repository.
     *
     * @param order  The order to which the item ID is to be added.
     * @param itemId The identifier of the item to be added to the order.
     */
    public void addItem(Order order, String itemId) {
        Set<String> items = order.getItems();
        items.add(itemId);
        order.setItems(items);
        repository.save(order);
    }

    /**
     * Checks if an order contains a specific item.
     *
     * @param order  The order to be checked.
     * @param itemId The identifier of the item to check for.
     * @return {@code true} if the order contains the item, {@code false} otherwise.
     */
    public boolean containItem(Order order, String itemId) {
        return order.getItems().contains(itemId);
    }

    /**
     * Retrieves the date when the order was placed.
     *
     * @param order The order from which to retrieve the date.
     * @return The date the order was placed.
     */
    public long getOrderDate(Order order) {
        return order.getOrderDate();
    }

    /**
     * Calculates the total cost of an order based on the cost of each line item.
     *
     * @param order The order for which the total cost is to be calculated.
     * @return The total cost of the order.
     */
    public int  getCost(Order order) {
        int cost = 0;
        for (Map.Entry<String, Line> entry : order.getLines().entrySet()) {
            cost += lineService.getCost(entry.getValue());
        }
        return cost;
    }

    /**
     * Calculates the total weight of an order based on the weight of each line item.
     *
     * @param order The order for which the total weight is to be calculated.
     * @return The total weight of the order.
     */
    public int getWeight(Order order) {
        Map<String, Line> lines = order.getLines();
        int weight = 0;
        for (Map.Entry<String, Line> entry : lines.entrySet()) {
            weight += lineService.getWeight(entry.getValue());
        }
        return weight;
    }

    /**
     * Retrieves a line item from an order based on the item name.
     *
     * @param order    The order from which to retrieve the line item.
     * @param itemName The name of the item in the line item to be retrieved.
     * @return The line item corresponding to the specified item name, or {@code null} if not found.
     */
    public Line getOrderLineByItemName(Order order, String itemName) {
        for (Line orderLine : order.getLines().values()) {
            if (lineService.getItemName(orderLine).equals(itemName)) {
                return orderLine;
            }
        }
        return null;
    }

    /**
     * Finalizes and processes the purchase of an order. This includes checking if the customer has sufficient credit,
     * if the drone can travel the required distance, and updating the relevant entities upon successful transaction.
     *
     * @param storeName The name of the store where the order was placed.
     * @param orderId   The ID of the order to be purchased.
     * @return A string indicating the outcome of the purchase process.
     */
    public String purchaseOrder(String storeName, String orderId) {
        TreeMap<String, Store> stores = storeService.getStoresAsTreeMap();

        if (stores.containsKey(storeName)) {
            Store store = stores.get(storeName);

            if (storeService.containOrder(store, orderId)) {
                Order order = storeService.getOrder(store, orderId);
                Customer customer = order.getCustomer();
                Drone drone = order.getDrone();
                DronePilot pilot = droneService.getCurrentPilot(drone);

                if (pilot != null) {
                    if (droneService.checkValidTravelDistance(drone)) {
                        if (customerService.getCredit(customer) >= getCost(order)) {
                             int newCredit = customer.getCredit() - getCost(order);
                            System.out.println("customer is : " + customer.getAccount() + " and order cost is :" + getCost(order));
                            int orderRevenue = getCost(order);
                            int newExpLevel = dronePilotService.getExpLevel(pilot) + 1;

                            customerService.setCredit(customer, newCredit);
                            storeService.addRevenue(store, orderRevenue);
                            droneService.travel(drone, customer.getXCoordinate(), customer.getYCoordinate());
                            dronePilotService.setExpLevel(pilot, newExpLevel);
                            droneService.removeOrder(drone, order);
                            if (droneService.getOrders(drone).isEmpty()) {
                                droneService.travel(drone, store.getXCoordinate(), store.getYCoordinate());
                            }
                            customerService.removeOrder(customer, order);
                            storeService.addCompletedOrders(store, orderId, order);
                            storeService.removeOrder(store, orderId);
                            int droneOrderSize = droneService.getOrders(drone).size() ;
                            storeService.addOverload(store, droneOrderSize);
                            System.out.println(OK_CHG_COMPLETE);
                            return OK_CHG_COMPLETE;
                        } else {
                            System.out.println(ERR_CUSTOMER_CREDIT_LOW_ORDER);
                            return ERR_CUSTOMER_CREDIT_LOW_ORDER;
                        }
                    } else {
                        System.out.println(DRONE_DISTANCE_NOT_VALID);
                        return DRONE_DISTANCE_NOT_VALID;
                    }
                } else {
                    System.out.println(ERR_DRONE_NEED_PILOT);
                    return ERR_DRONE_NEED_PILOT;
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
     * Cancels an existing order. This includes removing the order from the drone, customer, and store records.
     *
     * @param storeName The name of the store where the order was placed.
     * @param orderId   The ID of the order to be canceled.
     * @return A string indicating the outcome of the cancellation process.
     */
    public String cancelOrder(String storeName, String orderId) {
        TreeMap<String, Store> stores = storeService.getStoresAsTreeMap();
        if (!stores.containsKey(storeName)) {
            System.out.println(ERR_STORE_ID_NOT_EXIST);
            return ERR_STORE_ID_NOT_EXIST;
        } else {
            Store store = stores.get(storeName);
            if (!store.getOrders().containsKey(orderId)) {
                System.out.println(ERR_ORDER_ID_NOT_EXIST);
                return ERR_ORDER_ID_NOT_EXIST;
            } else {
                Order order = store.getOrders().get(orderId);
                Drone drone = order.getDrone();
                Customer customer = order.getCustomer();

                droneService.removeOrder(drone, order);
                customerService.removeOrder(customer, order);
                storeService.removeOrder(store, orderId);
                System.out.println(OK_CHG_COMPLETE);
                return OK_CHG_COMPLETE;
            }
        }
    }

    /**
     * Transfers an existing order from one drone to another. This method checks for the capacity of the new drone
     * and updates the order's assignment accordingly.
     *
     * @param storeName   The name of the store where the order was placed.
     * @param orderId     The ID of the order to be transferred.
     * @param newDroneId  The ID of the new drone to which the order is to be transferred.
     * @return A string indicating the outcome of the transfer process.
     */
    public String transferOrder(String storeName, String orderId, String newDroneId) {

        TreeMap<String, Store> stores = storeService.getStoresAsTreeMap();
        if (stores.containsKey(storeName)) {
            Store store = stores.get(storeName);

            if (storeService.containOrder(store, orderId)) {
                Order order = storeService.getOrder(store, orderId);

                if (storeService.containDrone(store, newDroneId)) {
                    Drone newDrone = storeService.getDrone(store, newDroneId);
                    Drone oldDrone = order.getDrone();

                    if ((newDrone.getLiftingCapacity() - droneService.getWeight(newDrone)) < orderService.getWeight(order)) {
                        System.out.println(ERR_DRONE_NOT_ENOUGH_CAP);
                        return ERR_DRONE_NOT_ENOUGH_CAP;
                    } else if ((newDrone.getId()).equals(oldDrone.getId())) {
                        System.out.println(OK_DRONE_NEW_CUR_SAME);
                        return OK_DRONE_NEW_CUR_SAME;
                    } else {
                        droneService.removeOrder(oldDrone, order);
                        droneService.addOrder(newDrone, order);
                        order.setDrone(newDrone);
                        storeService.addTransferCount(store);
                        System.out.println(OK_CHG_COMPLETE);
                        return OK_CHG_COMPLETE;
                    }
                } else {
                    System.out.println(ERR_DRONE_ID_NOT_EXIST);
                    return ERR_DRONE_ID_NOT_EXIST;
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
     * Formats and displays detailed information of a single order, including order ID and the details of each line item.
     *
     * @param order The order whose information is to be displayed.
     * @return A string representation of the order's detailed information.
     */
    public String display(Order order) {
        TreeMap<String, Line> lines = new TreeMap<>(order.getLines());
        StringBuilder orderIdInfo = new StringBuilder("orderID:" + order.getId());
        System.out.println(orderIdInfo);
        orderIdInfo.append('\n');
        for (Map.Entry<String, Line> entry : lines.entrySet()) {
            orderIdInfo.append(lineService.display(entry.getValue())).append('\n');
        }
        return orderIdInfo.toString();
    }

    /**
     * Displays information for all orders in a given store. This includes each order's ID and line item details.
     *
     * @param storeName The name of the store whose orders are to be displayed.
     * @return A string representation of all orders' information in the specified store.
     */
    public String displayOrders(String storeName) {
        TreeMap<String, Store> stores = storeService.getStoresAsTreeMap();
        if (!stores.containsKey(storeName)) {
            System.out.println(ERR_STORE_ID_NOT_EXIST);
            return ERR_STORE_ID_NOT_EXIST;
        } else {
            StringBuilder sb = new StringBuilder();
            Store store = stores.get(storeName);
            TreeMap<String, Order> orders = storeService.getOrders(store);
            for (Map.Entry<String, Order> entry : orders.entrySet()) {
                sb.append(display(entry.getValue()));
            }
            System.out.println(OK_DISPLAY_COMPLETE);
            sb.append(OK_DISPLAY_COMPLETE);
            return sb.toString();
        }
    }

    /**
     * Retrieves all orders from the repository and returns them as a TreeMap sorted alphabetically by order ID.
     * This is useful for displaying orders in a sorted manner.
     *
     * @return A TreeMap where each key is an order's ID and the value is the Order object.
     */
    public TreeMap<String, Order> getOrdersAsTreeMap() {
        List<Order> OrderList = repository.findAll();
        TreeMap<String, Order> orders = new TreeMap<>();
        for (Order order : OrderList) {
            orders.put(order.getId(), order);
        }
        return orders;
    }
}
