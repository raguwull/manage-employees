package com.employee_application.manage_employees.service.project;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee_application.manage_employees.exception.ResourceNotFoundException;
import com.employee_application.manage_employees.model.project.Project;
import com.employee_application.manage_employees.repository.ProjectRepository;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> getProjects(String username) {
        return projectRepository.findByUser(username)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot find projects for user: " + username));
    }
    
    public List<Project> getProjectByStatus(String username, String status){
    	List<Project> projects = getProjects(username);
    	List<Project> projects_status = new ArrayList<Project>();
    	for (Project project : projects) {
    		if (project.getStatus().equals(status)){
    			projects_status.add(project);
    		}
    	}
    	return projects_status;
    }
    
    public void saveProject(Project project) {
    	projectRepository.save(project);
    }
}