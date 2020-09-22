package com.employee.manager.paypal.EmployeeManagerPaypal.repository;


import com.employee.manager.paypal.EmployeeManagerPaypal.VO.EmployeeVO;
import com.employee.manager.paypal.EmployeeManagerPaypal.model.EmployeeModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;


public interface EmployeeRepository extends JpaRepository<EmployeeModel, Integer> {

    /**
     * this is findById method for finding the data of employees according to their IDs.
     * @param empId
     * @return
     */
    EmployeeModel findById(int empId);

    /**
     * this is findById method for finding the data of employees according to their IDs.
     * @param empName
     * @return
     */
    EmployeeModel findByEmpName(String empName);

}
