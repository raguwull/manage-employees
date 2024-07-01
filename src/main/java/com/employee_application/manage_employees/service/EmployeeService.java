package com.employee_application.manage_employees.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee_application.manage_employees.exception.DuplicateProjectException;
import com.employee_application.manage_employees.exception.ResourceNotFoundException;
import com.employee_application.manage_employees.model.Employee;
import com.employee_application.manage_employees.model.Project;
import com.employee_application.manage_employees.repository.EmployeeRepository;
import com.employee_application.manage_employees.repository.ProjectRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired 
    private ProjectRepository projectRepository;
    
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> saveEmployees(List<Employee> employees) {
        return employeeRepository.saveAll(employees);
    }

    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    } 

    public Employee findEmployeeById(long id) {
    	return employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
    }

    public void deleteEmployeeById(long id) {
        employeeRepository.deleteById(id);
    }

    public Employee updateEmployee(long id, Employee employeeDetails) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
        employee.setName(employeeDetails.getName());
        employee.setAge(employeeDetails.getAge()); 
        employee.setAddress(employeeDetails.getAddress());
        employee.setDepartment(employeeDetails.getDepartment());
        employee.setPosition(employeeDetails.getPosition());
        employee.setSalary(employeeDetails.getSalary());
        employee.setEmail(employeeDetails.getEmail());
        employee.setPhoneNumber(employeeDetails.getPhoneNumber());
        employee.setEmployeeStatus(employeeDetails.getEmployeeStatus());
        return employeeRepository.save(employee);
    } 

    public Employee partialUpdateEmployee(long id, Employee employeeDetails) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
        if (employeeDetails.getName() != null) {
            employee.setName(employeeDetails.getName());
        }
        if (employeeDetails.getAge() != 0) {
            employee.setAge(employeeDetails.getAge());
        }
        if (employeeDetails.getAddress() != null) {
            employee.setAddress(employeeDetails.getAddress());
        }
        if (employeeDetails.getDepartment() != null) {
            employee.setDepartment(employeeDetails.getDepartment());
        }
        if (employeeDetails.getPosition() != null) {
            employee.setPosition(employeeDetails.getPosition());
        }
        if (employeeDetails.getSalary() != 0) {
            employee.setSalary(employeeDetails.getSalary());
        }
        if (employeeDetails.getEmail() != null) {
            employee.setEmail(employeeDetails.getEmail());
        }
        if (employeeDetails.getPhoneNumber() != 0) {
            employee.setPhoneNumber(employeeDetails.getPhoneNumber());
        }
        if (employeeDetails.getEmployeeStatus() != null) {
            employee.setEmployeeStatus(employeeDetails.getEmployeeStatus());
        }
        return employeeRepository.save(employee);
    }

	public List<Project> getProjects(long id) {
		Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
		return employee.getProjects();
	}

	public Employee postProject(long employeeId, Project project) throws DuplicateProjectException {
        Employee employee = findEmployeeById(employeeId);

        for (Project existingProject : employee.getProjects()) {
            if (existingProject.getId() == project.getId()) {
                throw new DuplicateProjectException("Employee already associated with the project id: " + project.getId());
            }
        }

        Optional<Project> existingProjectByName = projectRepository.findByName(project.getName());
        if (existingProjectByName.isPresent()) {
            project.setId(existingProjectByName.get().getId());
        } else {
            project = projectRepository.save(project);
        }

        employee.getProjects().add(project);
        return employeeRepository.save(employee);
    }

}
