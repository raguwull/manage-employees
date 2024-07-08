//package com.employee_application.manage_employees.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.employee_application.manage_employees.model.Project;
//import com.employee_application.manage_employees.service.ProjectService;
//
//@RestController 
//@RequestMapping("/projects")
//public class ProjectController {
//	
//	@Autowired
//	private ProjectService projectService;
//	
//	@PostMapping("/{id}")
//	public ResponseEntity<Project> saveProject(@PathVariable long id, @RequestBody Project project) {
//		projectService.saveProject(project);
//		return new ResponseEntity<>(project, HttpStatus.CREATED);
//	}
//
//	@GetMapping
//	public ResponseEntity<List<Project>> getProjects(){
//		List<Project> projects = projectService.findAllProjects();
//		return new ResponseEntity<>(projects, HttpStatus.FOUND);
//	}
//}