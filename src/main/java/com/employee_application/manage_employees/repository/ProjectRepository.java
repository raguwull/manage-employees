package com.employee_application.manage_employees.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.employee_application.manage_employees.model.project.Project;


public interface ProjectRepository extends JpaRepository<Project, Long>{
	Optional<Project> findByName(String name);

	@Query("SELECT p FROM Project p JOIN p.users u WHERE u.username = :username")
    Optional<List<Project>> findByUser(@Param("username") String username);

	List<Project> findAllByStatus(String status);

	List<Project> findByNameContainingIgnoreCase(String name);
	
}
