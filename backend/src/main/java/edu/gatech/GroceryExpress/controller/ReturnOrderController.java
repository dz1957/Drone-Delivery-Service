package edu.gatech.GroceryExpress.controller;

import edu.gatech.GroceryExpress.service.ReturnOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ReturnOrderController {
    @Autowired
    private ReturnOrderService returnOrderService;

    //when an @RequestParam annotation is declared as map<string, string>
    // or multivaluemap<string, string> argument, the map is populated with all request parameters.
    @PostMapping("/return")
    public String requestReturn(@RequestParam String storeName, @RequestParam String orderId, @RequestParam String customerAccount,
                                @RequestParam String droneId, @RequestParam Map<String, String> itemAndQty,
                                @RequestParam Integer returnWindow, @RequestParam Integer maxReturnableOrders) {
        return returnOrderService.requestReturn(storeName, orderId, customerAccount, droneId, itemAndQty, returnWindow, maxReturnableOrders);
    }
}
