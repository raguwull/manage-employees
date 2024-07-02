package com.employee_application.manage_employees.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee_application.manage_employees.exception.DuplicateProjectException;
import com.employee_application.manage_employees.model.Employee;
import com.employee_application.manage_employees.model.Project;
import com.employee_application.manage_employees.service.EmployeeService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
 
    // Get CSRF token 
    // Synchronizer pattern method
    @GetMapping("/token")
    public ResponseEntity<CsrfToken> getCsrfToken(HttpServletRequest request){
    	CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
    	return new ResponseEntity<>(token, HttpStatus.OK);
    }
    
    // Employee CRUD Operations
    
    @PostMapping
    public ResponseEntity<List<Employee>> saveEmployees(@RequestBody List<Employee> employees) {
        List<Employee> savedEmployees = employeeService.saveEmployees(employees);
        return new ResponseEntity<>(savedEmployees, HttpStatus.CREATED);
    }
    
    @PostMapping("/{id}")
    public ResponseEntity<Employee> saveEmployee(@PathVariable long id, @RequestBody Employee employee) {
        Employee savedEmployee = employeeService.saveEmployee(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> findEmployees() {
        List<Employee> foundEmployees = employeeService.findAllEmployees();
        return new ResponseEntity<>(foundEmployees, HttpStatus.OK);
    } 
 
    @GetMapping("/{id}")
    public ResponseEntity<Employee> findEmployeeById(@PathVariable long id) {
        Employee foundEmployee = employeeService.findEmployeeById(id); 
        return new ResponseEntity<>(foundEmployee, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> deleteEmployeeById(@PathVariable long id) {
    	Employee foundEmployee = employeeService.findEmployeeById(id);
    	employeeService.deleteEmployeeById(id); 
    	return new ResponseEntity<>(foundEmployee, HttpStatus.OK);
    } 
     
    @PutMapping("/{id}")
    public ResponseEntity<Employee> putEmployeeById(@PathVariable long id, @RequestBody Employee employeeDetails) {
		Employee updatedEmployee = employeeService.updateEmployee(id, employeeDetails);
		return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }  
     
    @PatchMapping("/{id}")
    public ResponseEntity<Employee> patchEmployeeById(@PathVariable long id, @RequestBody Employee employeeDetails){
    	Employee updatedEmployee = employeeService.partialUpdateEmployee(id, employeeDetails);
		return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }
    
    // Project CRUD Operations   
    
    @GetMapping("/{employee_id}/projects") 
    public ResponseEntity<List<Project>> getProjects(@PathVariable long employee_id){
    	List<Project> projects = employeeService.getProjects(employee_id);
    	return new ResponseEntity<>(projects, HttpStatus.FOUND);
    }
    
    @PostMapping("/{employee_id}/projects") 
    public ResponseEntity<Employee> postProject(@PathVariable long employee_id, @RequestBody Project project) throws DuplicateProjectException{
    	Employee employee = employeeService.postProject(employee_id, project);
    	return new ResponseEntity<>(employee, HttpStatus.FOUND);
    }

} 