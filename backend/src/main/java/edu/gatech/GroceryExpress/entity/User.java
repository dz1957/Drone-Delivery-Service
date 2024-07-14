package edu.gatech.GroceryExpress.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.MappedSuperclass;
import javax.persistence.Table;


@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "UserTable")
public abstract class User {
    private String firstName;
    private String lastName;
    private String phoneNumber;
}
