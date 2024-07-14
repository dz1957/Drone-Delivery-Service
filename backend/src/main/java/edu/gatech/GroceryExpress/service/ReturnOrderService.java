package edu.gatech.GroceryExpress.service;

import edu.gatech.GroceryExpress.entity.*;
import edu.gatech.GroceryExpress.repository.ReturnOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static edu.gatech.GroceryExpress.util.Constant.*;


/**
 * Service class for handling return orders. This class provides methods to create
 * and process return orders, calculate refunds and return weights, and manage travel
 * logistics for drones involved in returns.
 * 
 * @author Team 30
 * @since Dec 1th, 2023
 */

@Service
public class ReturnOrderService {
    @Autowired
    private ReturnOrderRepository repository;
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
    @Autowired
    private DronePilotService dronePilotservice;
    @Autowired
    private CustomClockService customClockService;

      /**
     * Creates a return order with the specified details and stores it in the repository.
     *
     * @param store          The store associated with the return order.
     * @param order          The original order being returned.
     * @param customer       The customer making the return.
     * @param drone          The drone used for the return.
     * @param itemAndQty   A map of item names and their respective quantities being returned.
     * @return The created ReturnOrder object.
     */
    public ReturnOrder makeReturnOrder(Store store, Order order, Customer customer, Drone drone, Map<String, Integer> itemAndQty, Integer returnWindow, Integer maxReturnableOrders) {
        ReturnOrder returnOrder = new ReturnOrder(store, order, customer, drone, itemAndQty, customClockService.currentTimeMillis(), returnWindow, maxReturnableOrders);
        repository.save(returnOrder);
        return returnOrder;
    }

    /**
     * Retrieves the original order associated with the given return order.
     *
     * @param returnOrder The return order from which to retrieve the original order.
     * @return The original {@link Order} object associated with the specified return order.
     */
    public Order getOriginalOrder(ReturnOrder returnOrder) {
        return returnOrder.getOriginalOrder();
    }

    /**
     * Retrieves the customer associated with the given return order.
     *
     * @param returnOrder The return order from which to retrieve the customer.
     * @return The {@link Customer} object associated with the specified return order.
     */
    public Customer getCustomer(ReturnOrder returnOrder) {
        return returnOrder.getCustomer();
    }

    /**
     * Retrieves the return date of the given return order.
     * The return date is represented as a timestamp.
     *
     * @param returnOrder The return order from which to retrieve the return date.
     * @return The timestamp representing the return date of the return order.
     */
    public long getReturnDate(ReturnOrder returnOrder) {
        return returnOrder.getTimestamp();
    }


    /**
     * Processes a return request for an order. This method validates the return request
     * against various business rules such as store and order existence, drone availability,
     * and customer ownership. If the request is valid, it initiates the return process.
     *
     * @param storeName        The name of the store from which the order was made.
     * @param orderId          The ID of the order being returned.
     * @param customerAccount  The account ID of the customer making the return.
     * @param droneId          The ID of the drone to be used for the return.
     * @param itemAndQty     A map containing item names and their respective quantities for return.
     * @return A string indicating the result of the return request, such as an error message or confirmation.
     */
    public String requestReturn(String storeName, String orderId, String customerAccount,
                                String droneId, Map<String, String> itemAndQty, Integer returnWindow, Integer maxReturnableOrders) {
        Map<String, Integer> itemQtyMap = new HashMap<>();
        String[] keyValuePairs = itemAndQty.get("itemAndQty").split(",");
        for (int i = 0; i < keyValuePairs.length; i += 2) {
            String key = keyValuePairs[i];
            Integer value = Integer.valueOf(keyValuePairs[i + 1]);
            itemQtyMap.put(key, value);
        }
        TreeMap<String, Store> stores = storeService.getStoresAsTreeMap();
        if (!stores.containsKey(storeName)) {
            System.out.println(ERR_STORE_ID_NOT_EXIST);
            return ERR_STORE_ID_NOT_EXIST;
        } else {
            Store store = stores.get(storeName);
            if (!storeService.containCompletedOrder(store, orderId)) {
                System.out.println(ERR_ORDER_ID_NOT_EXIST);
                return ERR_ORDER_ID_NOT_EXIST;
            } else {
                Order order = storeService.getCompletedOrder(store, orderId);
                if (order.getOrderReturned()){
                    System.out.println(ERROR_ORDER_ALREADY_RETURNED);
                    return ERROR_ORDER_ALREADY_RETURNED;
                } else {
                    if (!storeService.getDrones(store).containsKey(droneId)) {
                        System.out.println(ERR_DRONE_ID_NOT_EXIST);
                        return ERR_DRONE_ID_NOT_EXIST;
                    } else {
                        Customer customer = orderService.getCustomer(order);
                        Drone drone = storeService.getDrone(store, droneId);
                        if (!customerService.getAccount(customer).equals(customerAccount)) {
                            System.out.println(ERR_NOT_BELONG_TO_CUSTOMER);
                            return ERR_NOT_BELONG_TO_CUSTOMER;
                        } else {
                            for (Map.Entry<String, Integer> entry : itemQtyMap.entrySet()) {
                                if (!orderService.containItem(order, entry.getKey())) {
                                    System.out.println(ERR_ITEM_ID_NOT_EXIST);
                                    return ERR_ITEM_ID_NOT_EXIST;
                                }
                            }
                            ReturnOrder returnRequest = makeReturnOrder(store, order, customer, drone, itemQtyMap, returnWindow, maxReturnableOrders);
                            String returnResult = initiateReturn(returnRequest);
                            System.out.println(returnResult);
                            return returnResult;
                        }
                    }
                }
            }
        }
    }

    /**
     * Initiates the return process for a given return order. This method checks various conditions
     * such as the return time window, order quantity accuracy, drone capabilities, and pilot availability.
     * If all conditions are met, the return process is executed.
     *
     * @param returnOrder The return order to process.
     * @return A string indicating the outcome of the initiation process, such as an error message or success confirmation.
     */
    public String initiateReturn(ReturnOrder returnOrder) {
        // Check if the return request is within the allowed time window
        long currentTime = customClockService.currentTimeMillis();
        long orderTime = orderService.getOrderDate(returnOrder.getOriginalOrder());
        long timeDiff = currentTime - orderTime;
        long returnWindowMilli = (long) returnOrder.getReturnWindow() * HOURS_PER_DAY * MILLIS_PER_HOUR;
        if (timeDiff > returnWindowMilli || returnWindowMilli==0) {
            return ERR_30_DAYS;
        } else {
            if (!isWithinMaxReturnableOrders(returnOrder)) {
                // Check if the return is within the max returnable orders limit
                return ERR_5_RETURNS;
            } else {
                for (Map.Entry<String, Integer> entry : returnOrder.getItemAndQty().entrySet()) {
                    String itemName = entry.getKey();
                    Integer returnQty = entry.getValue();
                    Line line = orderService.getOrderLineByItemName(returnOrder.getOriginalOrder(), itemName);
                    // Check if the quantities in the return request do not exceed the quantities in the order
                    if (line == null || returnQty > line.getQuantity()) {
                        return ERR_INVALID_QUANTITIES;
                    }
                }
                Drone drone = returnOrder.getDrone();
                // Check if the drone meets return shipping requirements
                if (drone.getCurrentPilot() == null) {
                    return ERR_DRONE_NEED_PILOT;
                } else {
                    if (!droneService.checkValidTravelDistance(drone)) {
                        return ERR_DRONE_FUEL_CAPACITY_LOW;
                    } else {
                        if (!(droneService.getOrders(drone).isEmpty() ||
                                (drone.getOrders().size() == 1 && drone.getOrders().get(0).getCustomer().getAccount().equals(customerService.getAccount(returnOrder.getCustomer()))))) {
                            return ERR_DRONE_HAS_OTHER_ORDER;
                        } else {
                            if (droneService.getWeight(drone) + calculateReturnWeight(returnOrder) > drone.getLiftingCapacity()) {
                                return ERR_DRONE_NOT_ENOUGH_CAP;
                            } else {
                                processReturn(returnOrder);
                                returnOrder.getOriginalOrder().setOrderReturned(true);
                                repository.save(returnOrder);
                                return OK_CHG_COMPLETE;
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Calculates the total refund amount for a given return order. The refund is calculated
     * based on the quantity and unit cost of each item in the return order.
     *
     * @param returnOrder The return order for which the refund is to be calculated.
     * @return The total refund amount for the return order.
     */
    public int calculateRefund(ReturnOrder returnOrder) {
        int refundAmount = 0;
        for (Map.Entry<String, Integer> entry : returnOrder.getItemAndQty().entrySet()) {
            String itemName = entry.getKey();
            Integer qty = entry.getValue();
            Line line = orderService.getOrderLineByItemName(returnOrder.getOriginalOrder(), itemName);
            Integer price = lineService.getUnitCost(line);
            refundAmount += price * qty;
        }
        return refundAmount;
    }

    /**
     * Calculates the total weight of items being returned in a given return order.
     * This method iterates over each item in the return order and calculates the
     * total weight based on the item's weight and quantity.
     *
     * @param returnOrder The return order for which the return weight is to be calculated.
     * @return The total weight of the returned items.
     */
    public int calculateReturnWeight(ReturnOrder returnOrder) {
        int returnWeight = 0;
        for (Map.Entry<String, Integer> entry : returnOrder.getItemAndQty().entrySet()) {
            String itemName = entry.getKey();
            Integer qty = entry.getValue();
            Line line = orderService.getOrderLineByItemName(returnOrder.getOriginalOrder(), itemName);
            returnWeight += line.getItem().getWeight() * qty;
        }
        return returnWeight;
    }

    /**
     * Processes a return order by updating relevant entities such as the customer's credit,
     * the drone pilot's experience level, and the store's revenue. Additionally, it handles
     * the drone's travel logistics to and from the customer and the store.
     *
     * @param returnOrder The return order to be processed.
     */
    public void processReturn(ReturnOrder returnOrder) {
        DronePilot pilot = droneService.getCurrentPilot(returnOrder.getDrone());
        int refund = calculateRefund(returnOrder);
        int newCredit = customerService.getCredit(returnOrder.getCustomer()) + refund;
        dronePilotservice.addOneExpLevel(pilot);
        customerService.setCredit(returnOrder.getCustomer(), newCredit);
        storeService.addRevenue(returnOrder.getStore(), (-refund));


        int xCoordinateCustomer = customerService.getXCoordinate(returnOrder.getCustomer());
        int yCoordinateCustomer = customerService.getYCoordinate(returnOrder.getCustomer());
        droneService.travel(returnOrder.getDrone(), xCoordinateCustomer, yCoordinateCustomer);
        int xCoordinateStore = storeService.getXCoordinate(returnOrder.getStore());
        int yCoordinateStore = storeService.getYCoordinate(returnOrder.getStore());
        droneService.travel(returnOrder.getDrone(), xCoordinateStore, yCoordinateStore);
        //Address address1 = customer.getAddress();
        //Address address2 = store.getAddress();
        //int distance = Math.abs(address1.getX_coordinate() - address2.getX_coordinate())
        //+Math.abs(address1.getY_coordinate() - address2.getY_coordinate());
    }


    /**
     * Checks whether a return order is within the maximum allowable number of returns
     * for a given customer at a specific store. This method ensures that a customer
     * does not exceed the limit for returnable orders within their order history.
     *
     * @param returnOrder The return order to check against the customer's order history.
     * @return {@code true} if the return order is within the maximum limit, {@code false} otherwise.
     */
    public boolean isWithinMaxReturnableOrders(ReturnOrder returnOrder) {
        TreeMap<String, Order> orderHistory = storeService.getStoreCompletedOrdersAsTreeMap(returnOrder.getStore());

        if (returnOrder.getMaxReturnableOrders()==0) {
            return false;
        }

        // If the customer's total orders are less than the max returnable order
        //, allow the return
        if (orderHistory.size() < returnOrder.getMaxReturnableOrders()) {
            return true;
        }

        // Start checking from the most recent order and go backwards
        int count = 0;

        for (Map.Entry<String, Order> entry : orderHistory.entrySet()) {
            Order historicalOrder = entry.getValue();
            if (returnOrder.getCustomer().equals(historicalOrder.getCustomer())) {
                count++;
                if (orderService.getOrderId(returnOrder.getOriginalOrder()).equals(orderService.getOrderId(historicalOrder))) {
                    return true;
                }
                if (count == returnOrder.getMaxReturnableOrders()) {
                    break;
                }
            }
        }
        return false;
    }

}
