package com.employee.manager.paypal.EmployeeManagerPaypal;

import org.dozer.DozerBeanMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.employee.manager.paypal.EmployeeManagerPaypal.model.EmployeeModel;
import com.employee.manager.paypal.EmployeeManagerPaypal.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;


import java.util.List;

@SpringBootApplication
public class EmployeeManagerPaypalApplication{

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagerPaypalApplication.class, args);
	}

		@Bean
		public DozerBeanMapper dozerBeanMapper(){
			return new DozerBeanMapper();
		}

}
