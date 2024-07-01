package com.employee_application.manage_employees.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee_application.manage_employees.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>{
	Optional<Project> findByName(String name);
}
