package com.employee_application.manage_employees.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="USER")
public class User {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long user_id;
	private String username;
	private String password;
	private String role;
	
}
