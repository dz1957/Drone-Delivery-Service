package edu.gatech.GroceryExpress.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "CustomerTable")
@NoArgsConstructor
public class Customer extends User {
    @Id
    private String account;
    private int rating;
    private int credit;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> orders;
    private int xCoordinate;
    private int yCoordinate;

    public Customer(String account, String firstName, String lastName, String phoneNumber, int rating, int credit, int xCoordinate, int yCoordinate) {
        super(firstName, lastName, phoneNumber);
        this.account = account;
        this.rating = rating;
        this.credit = credit;
        this.orders = new ArrayList<>();
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }
}
