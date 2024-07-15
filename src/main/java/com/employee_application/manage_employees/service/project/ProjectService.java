package com.employee_application.manage_employees.service.project;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.employee_application.manage_employees.exception.ResourceNotFoundException;
import com.employee_application.manage_employees.model.myuser.MyUser;
import com.employee_application.manage_employees.model.project.Project;
import com.employee_application.manage_employees.repository.ProjectRepository;

import jakarta.transaction.Transactional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    
//    @Autowired
//    private UserRepository userRepository;
   
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

	public Project getProjectById(long id) {
		return projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cannot find Project"));
	}
	
	public Project getProjectByName(String name) {
		return projectRepository.findByName(name).orElseThrow(() -> new ResourceNotFoundException("Cannot find Project"));
	}


	public List<Project> getAllProjects() {
		return projectRepository.findAll();
	}
	
	@Transactional
    public void deleteProject(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id " + projectId));

        for (MyUser user : project.getUsers()) {
            user.getProjects().remove(project);
        }

        projectRepository.deleteById(projectId);
    }

	public List<Project> getProjectByStatus(String status) {
		return projectRepository.findAllByStatus(status);
	}
	
	public Page<Project> getPaginatedProjects(Pageable pageable) {
	    return projectRepository.findAll(pageable);
	}

	public List<Project> getProjectByNameContainingIgnoreCase(String name) {
	    List<Project> projects = projectRepository.findByNameContainingIgnoreCase(name);
	    if (projects.size() == 0) {
	        throw new ResourceNotFoundException("Cannot find project");
	    }
	    return projects;
	}



}