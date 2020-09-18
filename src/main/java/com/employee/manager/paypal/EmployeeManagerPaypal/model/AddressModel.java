package com.employee.manager.paypal.EmployeeManagerPaypal.model;

/**
 * This is model class of address having addressId and houseNo as attributes.
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Table(name = "address")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressModel {
    @Id
    @Column(name = "address_id")
    private int addressId;
    @Column(name = "house_no")
    private String houseNo;
}
