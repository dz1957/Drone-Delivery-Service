package edu.gatech.GroceryExpress.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

@MappedSuperclass
@Data
@NoArgsConstructor
@Table(name = "EmployeeTable")
public class Employee extends User {
    private String taxId;
    private int monthEmployed;
    private int salary;

    public Employee(String firstName, String lastName, String phoneNumber, String taxId) {
        super(firstName, lastName, phoneNumber);
        this.taxId = taxId;
    }

    public Employee(String firstName, String lastName, String phoneNumber, String taxId, int monthEmployed, int salary) {
        super(firstName, lastName, phoneNumber);
        this.taxId = taxId;
        this.monthEmployed = monthEmployed;
        this.salary = salary;
    }
}
