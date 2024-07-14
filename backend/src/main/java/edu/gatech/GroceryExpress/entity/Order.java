package edu.gatech.GroceryExpress.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

@Data
@NoArgsConstructor
@Entity
@Table(name = "OrderTable")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rowId;
    private String id;
    @OneToOne
    private Store store;
    @OneToOne
    private Drone drone;
    @OneToOne
    private Customer customer;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Map<String, Line> lines;
    @ElementCollection
    private Set<String> items;
    private long orderDate;
    private Boolean orderReturned;

    public Order(String id, Store store, Drone drone, Customer customer, long orderDate) {
        this.id = id;
        this.store = store;
        this.drone = drone;
        this.customer = customer;
        this.lines = new TreeMap();
        this.items = new HashSet<>();
        this.orderDate = orderDate;
        this.orderReturned = false;

    }
}
