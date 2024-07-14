package edu.gatech.GroceryExpress.controller;

import edu.gatech.GroceryExpress.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for managing items in the store.
 * This controller provides endpoints for selling items, displaying them,
 * and handling item requests.
 */
@RestController
public class ItemController {
    @Autowired
    private ItemService itemService;

    /**
     * Adds an item for sale in the specified store with the provided details.
     *
     * @param storeName The name of the store where the item is to be sold.
     * @param itemName The name of the item to be sold.
     * @param weight The weight of the item.
     * @return A string message indicating the result of the item selling operation.
     */
    @PostMapping("/sellItem")
    public String sellItem(@RequestParam String storeName, @RequestParam String itemName, @RequestParam int weight) {
        return itemService.sellItem(storeName, itemName, weight);
    }

    /**
     * Displays all items available for sale in a specific store.
     *
     * @param storeName The name of the store whose items are to be displayed.
     * @return A string representation of the inventory of the store.
     */
    @GetMapping("/displayItems")
    public String displayItems(@RequestParam String storeName) {
        return itemService.displayItems(storeName);
    }

    /**
     * Processes a request for a particular item from an order in a store.
     *
     * @param storeName The name of the store from which the item is requested.
     * @param orderID The identifier of the order requesting the item.
     * @param itemId The identifier of the requested item.
     * @param quantity The quantity of the item requested.
     * @param unitPrice The unit price of the requested item.
     * @return A string message indicating the result of the item request operation.
     */
    @PutMapping("/requestItem")
    public String requestItem(@RequestParam String storeName, @RequestParam String orderID, @RequestParam String itemId,
                              @RequestParam int quantity, @RequestParam int unitPrice) {
        return itemService.requestItem(storeName, orderID, itemId, quantity, unitPrice);
    }
}
