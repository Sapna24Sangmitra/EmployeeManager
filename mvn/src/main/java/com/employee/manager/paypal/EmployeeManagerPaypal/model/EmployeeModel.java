package com.employee.manager.paypal.EmployeeManagerPaypal.model;

/**
 * This is the model class of employee having empId , empName and age as attributes.
 * It also has oneToMany maping with address and OneToOne mapping with passport.
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Table(name = "employee")
    @Entity
    public class EmployeeModel {

        @GeneratedValue
        @Id
        @Column(name = "emp_id")
        private long empId;
        @Column(name = "emp_name")
        private String empName;
        @Column(name = "age")
        private int age;


        /**
         * Here we are doing OneToMany mapping with the addressModel
         */
        @OneToMany
        @Column(name="addressList")
        private List<AddressModel> addressList;

        /**
         * Here we are doing OneToOne mapping with the passportModel.
         */
        @OneToOne
        private PassportModel passport;
    }
