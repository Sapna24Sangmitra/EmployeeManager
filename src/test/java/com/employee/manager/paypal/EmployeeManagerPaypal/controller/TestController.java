package com.employee.manager.paypal.EmployeeManagerPaypal.controller;

import com.employee.manager.paypal.EmployeeManagerPaypal.VO.AddressVO;
import com.employee.manager.paypal.EmployeeManagerPaypal.VO.EmployeeVO;
import com.employee.manager.paypal.EmployeeManagerPaypal.VO.PassportVO;
import com.employee.manager.paypal.EmployeeManagerPaypal.exception.EmployeeExists;
import com.employee.manager.paypal.EmployeeManagerPaypal.exception.EmployeeNotFound;
import com.employee.manager.paypal.EmployeeManagerPaypal.model.EmployeeModel;
import com.employee.manager.paypal.EmployeeManagerPaypal.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class TestController{

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Autowired
    EmployeeController employeeController;

    @MockBean
    EmployeeRepository employeeRepository;


//    @Test
//    public void testGetAllEmp() throws Exception {
//        PassportVO passportVO =new PassportVO(1,"abc");
//        AddressVO addressVO=new AddressVO(1,"xyz");
//        List<AddressVO> addressVOList =new ArrayList<>();
//        addressVOList.add(addressVO);
//        mockMvc.perform(get("/getAllEmp")).andExpect(status().isOk())
//                .andExpect(content().contentType("application/json"))
//                .andExpect(jsonPath("$.empId").value("1")).andExpect(jsonPath("[1].empName").value("abc"))
//                .andExpect(jsonPath("[2].age").value("22")).andExpect(jsonPath("[3].addressList").value(addressVOList))
//                .andExpect(jsonPath("[4].passport").value(passportVO));

    @Test
    public void testGetAllEmp() throws EmployeeNotFound
    {
        when(employeeRepository.findAll()).thenReturn(Stream.of(
                new EmployeeModel(1, "xyz", 20, null, null),
                new EmployeeModel(1, "xyz", 20, null, null))
                .collect(Collectors.toList()));
        //log.info("Heeeeeeeeeeelllllloooooo {}"+employeeController.getAllEmp().size());
        assertEquals(HttpStatus.OK,employeeController.getAllEmp().getStatusCode());
    }

    @Test
    public void testGetByID() throws EmployeeNotFound
    {
        int empId = 1;
        EmployeeModel employeeModel=  new EmployeeModel(1, "xyz", 20, null, null);
        EmployeeVO expected =new DozerBeanMapper().map(employeeModel,EmployeeVO.class);
        when(employeeRepository.findById(empId)).thenReturn(employeeModel);
        assertEquals(HttpStatus.OK,employeeController.getEmpById(empId).getStatusCode());
    }

    @Test
    public void testAddEmployee() throws EmployeeExists
    {
        PassportVO passportVO =new PassportVO(1,"abc");
        AddressVO addressVO=new AddressVO(1,"xyz");
        List<AddressVO> addressVOList =new ArrayList<>();
        addressVOList.add(addressVO);
        EmployeeVO employeeVO = new EmployeeVO(1,"pqrs",66,addressVOList, passportVO);
        EmployeeModel employeeModel = new DozerBeanMapper().map(employeeVO, EmployeeModel.class);
        log.debug("vo{}",employeeVO);
        when(employeeRepository.save(employeeModel)).thenReturn(employeeModel);
        assertEquals(HttpStatus.CREATED, employeeController.addEmp(employeeVO).getStatusCode());

    }

    @Test
    public void testDeleteByEmpId() throws EmployeeNotFound{
        int empId = 1;
        EmployeeModel employeeModel=  new EmployeeModel(1, "xyz", 20, null, null);
        EmployeeVO expected =new DozerBeanMapper().map(employeeModel,EmployeeVO.class);
        when(employeeRepository.findById(empId)).thenReturn(employeeModel);
        employeeController.deleteByEmpId(empId);
        assertEquals((HttpStatus.NO_CONTENT), employeeController.deleteByEmpId(empId).getStatusCode());
    }

}
