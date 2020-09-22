package com.employee.manager.paypal.EmployeeManagerPaypal.VO;


import com.employee.manager.paypal.EmployeeManagerPaypal.model.PassportModel;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.employee.manager.paypal.EmployeeManagerPaypal.model.AddressModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dozer.Mapping;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeVO {
    private int empId;
    @Mapping("empName")
    private String Name;
    private int age;

    private List<AddressVO> addressList;
    private PassportVO passport;

}
