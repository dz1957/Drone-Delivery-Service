package edu.gatech.GroceryExpress.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Map;
import java.util.Objects;

import static edu.gatech.GroceryExpress.util.Constant.DEFAULT_MAX_RETURNABLE_ORDERS;
import static edu.gatech.GroceryExpress.util.Constant.DEFAULT_RETURN_WINDOW;

@Data
@Entity
@NoArgsConstructor
@Table(name = "ReturnOrderTable")
public class ReturnOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rowId;
    @OneToOne
    private Store store;
    @OneToOne
    private Order originalOrder;
    @OneToOne
    private Customer customer;
    @OneToOne
    private Drone drone;
    private long timestamp;
    private Integer returnWindow;
    private Integer maxReturnableOrders;
    @ElementCollection
    private Map<String, Integer> itemAndQty;

    public ReturnOrder(Store store, Order order, Customer customer, Drone drone, Map<String, Integer> itemAndQty, long timestamp, Integer returnWindow, Integer maxReturnableOrders) {
        this.store = store;
        this.originalOrder = order;
        this.customer = customer;
        this.drone = drone;
        this.itemAndQty = itemAndQty;
        this.timestamp = timestamp;
        // Returns the first parameter if it is not null, the second parameter otherwise. If both parameters are null, it throws NullPointerException
        this.returnWindow = Objects.requireNonNullElse(returnWindow, DEFAULT_RETURN_WINDOW);
        this.maxReturnableOrders = Objects.requireNonNullElse(maxReturnableOrders, DEFAULT_MAX_RETURNABLE_ORDERS);
    }
}
