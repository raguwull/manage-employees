//package com.employee_application.manage_employees.model;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import org.hibernate.annotations.CreationTimestamp;
//
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.JoinTable;
//import jakarta.persistence.ManyToMany;
//import jakarta.persistence.Table;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Entity
//@Setter
//@Getter
//@AllArgsConstructor
//@NoArgsConstructor
//@Table(name="EMPLOYEE")
//public class Employee{
//	@Id
//	@GeneratedValue(strategy= GenerationType.IDENTITY)
//	@Column(name="USER_ID")
//	private long id;	
//	
//	@Column(name="USERNAME", unique=true)
//	private String username;
//	
//	@Column(name="PASSWORD")
//	private String password;
//	
//	@Column(name="ROLE")
//	private String role;
//	
//    private String name;
//    private int age;
//    private String address;
//    private String department;
//    private String position;
//    
//    @Column(nullable=false)
//    private double salary;
//    private String email;
//     
//    @CreationTimestamp
//    @Column(name="DATEOFJOINING")
//    private LocalDateTime dateOfJoining;
//    
//    @Column(name="EMPLOYEESTATUS", nullable=false)
//    private String employeeStatus = "Active";
//
//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "EMPLOYEE_PROJECT",
//            joinColumns = @JoinColumn(name = "USER_ID"),
//            inverseJoinColumns = @JoinColumn(name = "PROJECT_ID")
//    )
//    private List<Project> projects;
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public int getAge() {
//		return age;
//	}
//
//	public void setAge(int age) {
//		this.age = age;
//	}
//
//	public String getAddress() {
//		return address;
//	}
//
//	public void setAddress(String address) {
//		this.address = address;
//	}
//
//	public String getDepartment() {
//		return department;
//	}
//
//	public void setDepartment(String department) {
//		this.department = department;
//	}
//
//	public String getPosition() {
//		return position;
//	}
//
//	public void setPosition(String position) {
//		this.position = position;
//	}
//
//	public double getSalary() {
//		return salary;
//	}
//
//	public void setSalary(double salary) {
//		this.salary = salary;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public LocalDateTime getDateOfJoining() {
//		return dateOfJoining;
//	}
//
//	public void setDateOfJoining(LocalDateTime dateOfJoining) {
//		this.dateOfJoining = dateOfJoining;
//	}
//
//	public String getEmployeeStatus() {
//		return employeeStatus;
//	}
//
//	public void setEmployeeStatus(String employeeStatus) {
//		this.employeeStatus = employeeStatus;
//	}
//
//	public List<Project> getProjects() {
//		return projects;
//	}
//
//	public void setProjects(List<Project> projects) {
//		this.projects = projects;
//	}
//
//	public long getId() {
//		return id;
//	}
//
//	public void setId(long id) {
//		this.id = id;
//	}
//
//	public String getUsername() {
//		return username;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	public String getRole() {
//		return role;
//	}
//
//	public void setRole(String role) {
//		this.role = role;
//	}
//
//	@Override
//	public String toString() {
//		return "Employee [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role + ", name="
//				+ name + ", age=" + age + ", address=" + address + ", department=" + department + ", position="
//				+ position + ", salary=" + salary + ", email=" + email + ", dateOfJoining=" + dateOfJoining
//				+ ", employeeStatus=" + employeeStatus + ", projects=" + projects + "]";
//	}
//    
//}