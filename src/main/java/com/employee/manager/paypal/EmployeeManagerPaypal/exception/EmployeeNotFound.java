package com.employee.manager.paypal.EmployeeManagerPaypal.exception;

public class EmployeeNotFound extends Exception {
    public EmployeeNotFound(String id) {

        super(String.format("Employee Not Found of id %d ", id));
    }
}
