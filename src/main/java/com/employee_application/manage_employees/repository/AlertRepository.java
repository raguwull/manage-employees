package com.employee_application.manage_employees.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee_application.manage_employees.model.Alert.Alert;

public interface AlertRepository extends JpaRepository<Alert, Long>{

	long countByResolved(boolean b);
	
}
