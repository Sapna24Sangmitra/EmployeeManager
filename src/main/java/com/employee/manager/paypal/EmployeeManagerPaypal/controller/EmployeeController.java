package com.employee.manager.paypal.EmployeeManagerPaypal.controller;

/**
 * This is the controller class.
 */


import com.employee.manager.paypal.EmployeeManagerPaypal.VO.EmployeeVO;
import com.employee.manager.paypal.EmployeeManagerPaypal.exception.EmployeeNotFound;
import com.employee.manager.paypal.EmployeeManagerPaypal.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class EmployeeController{

    @Autowired
    private EmployeeService employeeService;

    /**
     * @PostMapping to create new employees data
     * @param
     */
    @PostMapping("/vo")
    public void addEmp(@RequestBody EmployeeVO employeeVO) {
        log.debug("emppppp {}", employeeVO.getAddressList());
        log.debug("method name:{}, address list size: {}", "add employee", employeeVO.getEmpName() == null ? -1 : employeeVO.getEmpName());
        employeeService.createEmp(employeeVO);


    }

    /**
     * @GetMapping to get all employees
     * @return
     */
    @GetMapping("/vo")
    public List<EmployeeVO> getAllEmp() {
        return employeeService.findAllEmp();

    }


    /**
     * @GetMapping to get the data of employee by their IDs.
     * @param empId
     * @return
     */
    @GetMapping("/vo/{empId}")
    public EmployeeVO getEmpById(@PathVariable("empId") long empId){
        return employeeService.findByID(empId);
    }

}
