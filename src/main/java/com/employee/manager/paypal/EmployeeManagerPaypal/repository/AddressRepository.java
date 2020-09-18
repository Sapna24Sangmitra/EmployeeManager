package com.employee.manager.paypal.EmployeeManagerPaypal.repository;

import com.employee.manager.paypal.EmployeeManagerPaypal.model.AddressModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddressModel, Integer> {
}
