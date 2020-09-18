package com.employee.manager.paypal.EmployeeManagerPaypal.serviceImpl;

import com.employee.manager.paypal.EmployeeManagerPaypal.VO.EmployeeVO;

import java.util.List;

public interface EmployeeServiceImpl {
    public void createEmp(EmployeeVO empVO);
    public List<EmployeeVO> findAllEmp();
    public EmployeeVO findByID(long empId);
}
