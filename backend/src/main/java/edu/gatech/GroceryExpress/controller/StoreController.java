package edu.gatech.GroceryExpress.controller;

import edu.gatech.GroceryExpress.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StoreController {
    @Autowired
    private StoreService storeService;

    @PostMapping("/store")
    public String makeStore(@RequestParam String storeName, @RequestParam int initialRevenue, @RequestParam int x, @RequestParam int y) {
        return storeService.makeStore(storeName, initialRevenue, x, y);
    }

    @GetMapping("/stores")
    public String displayStores() {
        return storeService.displayStores();
    }

    @GetMapping("/displayEfficiency")
    // TODO: modify returned content
    public String displayEfficiency() {
        return storeService.displayEfficiency();
    }
}
