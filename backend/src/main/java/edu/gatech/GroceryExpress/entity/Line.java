package edu.gatech.GroceryExpress.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "LineTable")
public class Line {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rowId;
    @OneToOne
    private Item item;
    private int quantity;
    private int price;

    public Line(Item item, int quantity, int price) {
        this.item = item;
        this.quantity = quantity;
        this.price = price;
    }
}
