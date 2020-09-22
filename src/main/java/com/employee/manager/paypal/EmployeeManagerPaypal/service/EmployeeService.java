package com.employee.manager.paypal.EmployeeManagerPaypal.service;

/**
 * This is the service class.
 * Here we are defining the methods to createEmployees, getEmployees and getEmployeesByID.
 */

import com.employee.manager.paypal.EmployeeManagerPaypal.VO.EmployeeVO;
import com.employee.manager.paypal.EmployeeManagerPaypal.exception.EmployeeExists;
import com.employee.manager.paypal.EmployeeManagerPaypal.exception.EmployeeNotFound;
import com.employee.manager.paypal.EmployeeManagerPaypal.model.EmployeeModel;

import java.util.List;


public interface EmployeeService{
    int createEmp(EmployeeVO empVO) throws EmployeeExists;
    List<EmployeeVO> findAllEmp();
    EmployeeVO findByID(int empId) throws EmployeeNotFound;
    EmployeeVO findByEmpName(String empName) throws EmployeeNotFound;
    public void deleteByEmpId(int empId) throws EmployeeNotFound;
}












