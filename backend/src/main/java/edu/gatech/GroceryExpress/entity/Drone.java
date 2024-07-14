package edu.gatech.GroceryExpress.entity;

import edu.gatech.GroceryExpress.util.DroneStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "DroneTable")
public class Drone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rowId;
    private String id;
    @OneToOne
    private Store store;
    private int liftingCapacity;
    private int fuel;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> orders;
    @OneToOne
    private DronePilot currentPilot;
    private DroneStatus status;
    private int fuelCapacity;
    // the rate is in cm per millisecond
    private int speed;
    // the rate is in mAh per cm
    private int fuelRate;
    // the rate is in mAh per millisecond
    private int refuelingRate;
    private int xCoordinate;
    private int yCoordinate;

    public Drone(String id, Store store, int liftingCapacity, int fuel, int fuelCapacity, int speed, int fuelRate, int refuelingRate, int xCoordinate, int yCoordinate) {
        this.id = id;
        this.store = store;
        this.liftingCapacity = liftingCapacity;
        this.fuel = fuel;
        this.orders = new ArrayList<>();
        this.currentPilot = null;
        this.status = DroneStatus.AVAILABLE;
        this.fuelCapacity = fuelCapacity;
        this.speed = speed;
        this.fuelRate = fuelRate;
        this.refuelingRate = refuelingRate;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }
}
