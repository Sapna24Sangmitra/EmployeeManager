package com.employee.manager.paypal.EmployeeManagerPaypal.exception;

public class EmployeeExists extends Exception{

        public EmployeeExists(int id) {

            super(String.format("Employee Already Exists of id %d ", id));
        }

    }
