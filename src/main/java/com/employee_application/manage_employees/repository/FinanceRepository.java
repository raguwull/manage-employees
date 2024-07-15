package com.employee_application.manage_employees.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee_application.manage_employees.model.finance.Finance;

@Repository
public interface FinanceRepository extends JpaRepository<Finance, Long>{

}
