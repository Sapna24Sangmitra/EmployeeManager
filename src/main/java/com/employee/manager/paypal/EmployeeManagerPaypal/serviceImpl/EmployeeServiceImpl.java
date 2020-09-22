package com.employee.manager.paypal.EmployeeManagerPaypal.serviceImpl;

/**
 * This is the service impl class
 * Here we are implementing the methods defined in service class
 */

import com.employee.manager.paypal.EmployeeManagerPaypal.VO.EmployeeVO;
import com.employee.manager.paypal.EmployeeManagerPaypal.exception.EmployeeExists;
import com.employee.manager.paypal.EmployeeManagerPaypal.exception.EmployeeNotFound;
import com.employee.manager.paypal.EmployeeManagerPaypal.model.AddressModel;
import com.employee.manager.paypal.EmployeeManagerPaypal.model.EmployeeModel;
import com.employee.manager.paypal.EmployeeManagerPaypal.model.PassportModel;
import com.employee.manager.paypal.EmployeeManagerPaypal.repository.AddressRepository;
import com.employee.manager.paypal.EmployeeManagerPaypal.repository.EmployeeRepository;
import com.employee.manager.paypal.EmployeeManagerPaypal.repository.PassportRepository;
import com.employee.manager.paypal.EmployeeManagerPaypal.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PassportRepository passportRepository;

    @Autowired
    private DozerBeanMapper dozerBeanMapper;


    /**
     * Method to create employee
     *
     * @param employeeVO
     */
    public int createEmp(EmployeeVO employeeVO) throws EmployeeExists {

        List<AddressModel> addressModels = new ArrayList<>();
        employeeVO.getAddressList().stream().forEach(addressVO ->
                {
                    AddressModel addressModel = dozerBeanMapper.map(addressVO, AddressModel.class);
                    addressModels.add(addressModel);
                }
        );
        addressRepository.saveAll(addressModels);
        PassportModel passportModel = dozerBeanMapper.map(employeeVO.getPassport(), PassportModel.class);
        passportRepository.save(passportModel);
      EmployeeModel employeeModel=  dozerBeanMapper.map(employeeVO,EmployeeModel.class);
        employeeModel.setAddressList(addressModels);
        employeeModel.setPassport(passportModel);
        EmployeeModel emp = null;

        List<EmployeeModel> employeeModelList =  employeeRepository.findAll();
        if(employeeModelList.size() != 0) {
            boolean ifExists = employeeModelList.stream().anyMatch(employee -> {
                        if (employee.getEmpId() == employeeModel.getEmpId()) {
                            return true;
                        }
                        return false;
                    }
            );

            if (ifExists) {
                throw new EmployeeExists(employeeModel.getEmpId());//Already existing ecepti
            }
        }
               emp=employeeRepository.save(employeeModel);
            if (emp != null) {
                return 1;
            } else {
                return 0;
            }
        }



    /**
     * Method to find all employees
     * @return
     */
    @Override
    public List<EmployeeVO> findAllEmp() {
        List<EmployeeModel> employeeModels = employeeRepository.findAll();
        List<EmployeeVO> employeeVOS = new ArrayList<>();
        employeeModels.stream().forEach(employeeModel -> {
        EmployeeVO employeeVO = dozerBeanMapper.map(employeeModel, EmployeeVO.class);
          employeeVOS.add(employeeVO);
        });
        log.debug("model{}", employeeModels);
        log.debug("vo{}", employeeVOS);
        return employeeVOS;
    }


    /**
     * Method to find employee by id
     * @param empId
     * @return
     */
    public EmployeeVO findByID(int empId) throws EmployeeNotFound{
        EmployeeModel employeeModel = employeeRepository.findById(empId);
        if(employeeModel == null) {
            throw new EmployeeNotFound(empId);
        } else {
            EmployeeVO employeeVO = dozerBeanMapper.map(employeeModel, EmployeeVO.class);
            return employeeVO;
        }
    }


    /**
     * Method to find employee by name
     * @param empName
     * @return
     */
    public EmployeeVO findByEmpName(String empName) throws EmployeeNotFound{
        EmployeeModel employeeModel = employeeRepository.findByEmpName(empName);
        if(employeeModel == null) {
            throw new EmployeeNotFound(empName);
        } else {
            EmployeeVO employeeVO = dozerBeanMapper.map(employeeModel, EmployeeVO.class);
            return employeeVO;
        }
    }

    public void deleteByEmpId(int empId) throws EmployeeNotFound {
        EmployeeModel employeeModel = employeeRepository.findById(empId);
        if (employeeModel == null) {
            throw new EmployeeNotFound(empId);
        } else
            employeeRepository.deleteById(empId);
    }

}

