package com.employee_application.manage_employees.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.employee_application.manage_employees.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
}