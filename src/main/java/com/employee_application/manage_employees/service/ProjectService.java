package com.employee_application.manage_employees.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee_application.manage_employees.model.Project;
import com.employee_application.manage_employees.repository.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository repository;
	
	public void saveProject(Project project) {
		repository.save(project);
	}

	public List<Project> findAllProjects() {
		return repository.findAll();
	}
}
