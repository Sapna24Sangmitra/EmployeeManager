package com.employee.manager.paypal.EmployeeManagerPaypal.service;

/**
 * This is the service class.
 * Here we are defining the methods to createEmployees, getEmployees and getEmployeesByID.
 */

import com.employee.manager.paypal.EmployeeManagerPaypal.ServiceImpl.EmployeeServiceImpl;
import com.employee.manager.paypal.EmployeeManagerPaypal.VO.EmployeeVO;
import com.employee.manager.paypal.EmployeeManagerPaypal.model.AddressModel;
import com.employee.manager.paypal.EmployeeManagerPaypal.model.EmployeeModel;
import com.employee.manager.paypal.EmployeeManagerPaypal.model.PassportModel;
import com.employee.manager.paypal.EmployeeManagerPaypal.repository.AddressRepository;
import com.employee.manager.paypal.EmployeeManagerPaypal.repository.EmployeeRepository;
import com.employee.manager.paypal.EmployeeManagerPaypal.repository.PassportRepository;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class EmployeeService implements EmployeeServiceImpl {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PassportRepository passportRepository;

    /**
     * Method to create employee
     * @param empVO
     */
    public void createEmp(EmployeeVO empVO) {

        log.debug("hello {}",empVO);

        List<AddressModel> addressModelList = new ArrayList<>();
        empVO.getAddressList().stream().forEach(addressVO -> {
            AddressModel addressModel = new DozerBeanMapper().map(addressVO, AddressModel.class);
            addressModelList.add(addressModel);
        });
        addressRepository.saveAll(addressModelList);

        PassportModel passportModel =  new DozerBeanMapper().map(empVO.getPassport(),PassportModel.class);
        passportRepository.save(passportModel);


        EmployeeModel emp = new DozerBeanMapper().map(empVO, EmployeeModel.class);
        emp.setAddressList(addressModelList);
        emp.setPassport(passportModel);
        employeeRepository.save(emp);
    }


    /**
     * Method to find all employees
     * @return
     */
    public List<EmployeeVO> findAllEmp() {
        Mapper mapper = new DozerBeanMapper();
        List<EmployeeModel> employeeModels=  employeeRepository.findAll();
        List<EmployeeVO> employeeVOS=mapper.map(employeeModels, List.class);
        return employeeVOS;
    }


    /**
     * Method to find employee by id
     * @param empId
     * @return
     */
    public EmployeeVO findByID(long empId) {
        EmployeeModel employeeModel= employeeRepository.findById(empId);
        Mapper mapper = new DozerBeanMapper();
        EmployeeVO employeeVO= mapper.map(employeeModel, EmployeeVO.class);
        return employeeVO;
    }
}












