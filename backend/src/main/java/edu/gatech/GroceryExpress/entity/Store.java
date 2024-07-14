package edu.gatech.GroceryExpress.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Map;
import java.util.TreeMap;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "StoreTable")
public class Store {
    @Id
    private String name;
    private int revenue;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Map<String, Item> items;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Map<String, Drone> drones;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Map<String, Order> orders;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Map<String, Order> completedOrders;
    private int transferCount;
    private int overload;
    private int xCoordinate;
    private int yCoordinate;

    public Store(String name, int revenue, int xCoordinate, int yCoordinate) {
        this.name = name;
        this.revenue = revenue;
        this.items = new TreeMap<>();
        this.drones = new TreeMap<>();
        this.orders = new TreeMap<>();
        this.completedOrders = new TreeMap<>();
        this.transferCount = 0;
        this.overload = 0;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }
}
