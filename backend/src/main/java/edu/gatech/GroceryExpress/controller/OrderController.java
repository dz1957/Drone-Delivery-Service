package edu.gatech.GroceryExpress.controller;

import edu.gatech.GroceryExpress.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for managing orders within the store.
 * This controller provides endpoints for creating, displaying,
 * purchasing, cancelling, and transferring orders.
 */
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * Starts a new order with the provided details in the specified store.
     *
     * @param storeName The name of the store where the order is to be started.
     * @param orderId The unique identifier for the new order.
     * @param droneId The identifier of the drone to be associated with the order.
     * @param customerAccount The account identifier of the customer placing the order.
     * @return A string message indicating the result of the operation.
     */
    @PostMapping("/startOrder")
    public String startOrder(@RequestParam String storeName, @RequestParam String orderId, @RequestParam String droneId, @RequestParam String customerAccount) {
        return orderService.startOrder(storeName, orderId, droneId, customerAccount);
    }

    /**
     * Displays all orders associated with a specific store.
     *
     * @param storeName The name of the store whose orders are to be displayed.
     * @return A string representation of the orders in the store.
     */
    @GetMapping("/displayOrders")
    public String displayOrders(@RequestParam String storeName) {
        return orderService.displayOrders(storeName);
    }

    /**
     * Finalizes the purchase of an order in the specified store.
     *
     * @param storeName The name of the store where the order is placed.
     * @param orderId The unique identifier of the order to be purchased.
     * @return A string message indicating the result of the purchase operation.
     */
    @PutMapping("/purchaseOrder")
    public String purchaseOrder(@RequestParam String storeName, @RequestParam String orderId) {
        return orderService.purchaseOrder(storeName, orderId);
    }

    /**
     * Cancels an existing order in the specified store.
     *
     * @param storeName The name of the store where the order is placed.
     * @param orderId The unique identifier of the order to be cancelled.
     * @return A string message indicating the result of the cancellation operation.
     */
    @DeleteMapping("/cancelOrder")
    public String cancelOrder(@RequestParam String storeName, @RequestParam String orderId) {
        return orderService.cancelOrder(storeName, orderId);
    }

    /**
     * Transfers an existing order to a new drone within the same store.
     *
     * @param storeName The name of the store where the order is placed.
     * @param orderId The unique identifier of the order to be transferred.
     * @param newDroneId The identifier of the new drone to which the order should be transferred.
     * @return A string message indicating the result of the transfer operation.
     */
    @PutMapping("/transferOrder")
    public String transferOrder(@RequestParam String storeName, @RequestParam String orderId, @RequestParam String newDroneId) {
        return orderService.transferOrder(storeName, orderId, newDroneId);
    }
}
