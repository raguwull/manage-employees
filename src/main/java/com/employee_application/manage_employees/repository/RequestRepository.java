package com.employee_application.manage_employees.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee_application.manage_employees.model.request.Request;

public interface RequestRepository extends JpaRepository<Request, Long>{

	long countByStatus(String string);

}
