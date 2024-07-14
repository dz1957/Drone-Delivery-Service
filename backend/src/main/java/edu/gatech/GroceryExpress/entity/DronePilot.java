package edu.gatech.GroceryExpress.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "DronePilotTable")
public class DronePilot extends Employee {
    @Id
    private String account;
    private String licenseId;
    private int expLevel;
    @OneToOne
    private Drone drone;

    public DronePilot(String account, String firstName, String lastName,
                      String phoneNumber, String taxId, String licenseId, int expLevel) {
        super(firstName, lastName, phoneNumber, taxId);
        this.account = account;
        this.licenseId = licenseId;
        this.expLevel = expLevel;
        this.drone = null;
    }
}
