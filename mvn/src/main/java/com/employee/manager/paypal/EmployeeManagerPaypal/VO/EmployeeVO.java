package com.employee.manager.paypal.EmployeeManagerPaypal.VO;


import com.employee.manager.paypal.EmployeeManagerPaypal.model.PassportModel;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.employee.manager.paypal.EmployeeManagerPaypal.model.AddressModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class EmployeeVO {
    private long empId;
    private String empName;
    private int age;

    private List<AddressVO> addressList;
    private PassportVO passport;

}