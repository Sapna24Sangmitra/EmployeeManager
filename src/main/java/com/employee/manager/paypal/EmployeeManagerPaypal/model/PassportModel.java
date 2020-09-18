package com.employee.manager.paypal.EmployeeManagerPaypal.model;

/**
 * This is the model class of passport having passportId and employeeName as attributes.
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Table(name = "passportModel")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassportModel{
    @Id
    @Column(name = "passport_id")
    private int passportId;
    @Column(name = "emp_name")
    private String empName;
}

