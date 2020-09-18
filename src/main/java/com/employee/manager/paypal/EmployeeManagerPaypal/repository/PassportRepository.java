package com.employee.manager.paypal.EmployeeManagerPaypal.repository;


import com.employee.manager.paypal.EmployeeManagerPaypal.model.PassportModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassportRepository extends JpaRepository<PassportModel, Integer> {
}
