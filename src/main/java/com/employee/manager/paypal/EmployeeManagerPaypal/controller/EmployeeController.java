package com.employee.manager.paypal.EmployeeManagerPaypal.controller;

/**
 * This is the controller class.
 */


import com.employee.manager.paypal.EmployeeManagerPaypal.VO.EmployeeVO;
import com.employee.manager.paypal.EmployeeManagerPaypal.exception.EmployeeExists;
import com.employee.manager.paypal.EmployeeManagerPaypal.exception.EmployeeNotFound;
import com.employee.manager.paypal.EmployeeManagerPaypal.repository.EmployeeRepository;
import com.employee.manager.paypal.EmployeeManagerPaypal.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * @param
     * @throws EmployeeExists
     * @PostMapping to create new employees data
     */
    @PostMapping("/vo")
    public ResponseEntity<Integer> addEmp(@RequestBody EmployeeVO employeeVO) throws EmployeeExists {
        log.debug("emppppp {}", employeeVO.getAddressList());
        log.debug("method name:{}, address list size: {}", "add employee", employeeVO.getName() == null ? -1 : employeeVO.getName());
        int emp = employeeService.createEmp(employeeVO);
        return new ResponseEntity<>(emp, HttpStatus.CREATED);
    }


    /**
     * @return
     * @throws EmployeeNotFound
     * @GetMapping to get all employees
     */
    @GetMapping("/getAllEmp")
    public ResponseEntity<List<EmployeeVO>> getAllEmp() throws EmployeeNotFound {
        return new ResponseEntity<>(employeeService.findAllEmp(), HttpStatus.OK);

    }


    /**
     * @param empId
     * @return
     * @throws EmployeeNotFound
     * @GetMapping to get the data of employee by their IDs.
     */
    @GetMapping("/getById/{empId}")
    public ResponseEntity<EmployeeVO> getEmpById(@PathVariable int empId) throws EmployeeNotFound {
        return new ResponseEntity<>(employeeService.findByID(empId), HttpStatus.OK);
    }


    /**
     * @param empName
     * @return
     * @throws EmployeeNotFound
     * @GetMapping to get the data of employee by their names.
     */

    @GetMapping("/getByName/{empName}")
    public EmployeeVO findByEmpName(@PathVariable("empName") String empName) throws EmployeeNotFound {
        return employeeService.findByEmpName(empName);
    }

    /**
     * DeleteMapping to delete the data of a particular emp acc to their IDs.
     *
     * @param empId
     * @return
     * @throws EmployeeNotFound
     */
    @DeleteMapping("/deleteByEmpId/{empId}")
    public ResponseEntity<Void> deleteByEmpId(@PathVariable int empId) throws EmployeeNotFound {
        employeeService.deleteByEmpId(empId);
        return ResponseEntity.noContent().build();
    }
}



