package com.employee_application.manage_employees.model.project;

import java.util.Date;
import java.util.List;

import com.employee_application.manage_employees.model.myuser.MyUser;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="PROJECT")
public class Project {
	
	@Id 
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "PROJECT_ID")
	private long id;

	private String name;
	
	private String description;
	private Date startDate;
	private int duration;
	
	@Column(nullable= false) 
	private String status;

	@JsonIgnore
	@ManyToMany(mappedBy = "projects", fetch = FetchType.LAZY)
	private List<MyUser> users;
	
	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public List<MyUser> getUsers() {
		return users;
	}

	public void setUsers(List<MyUser> users) {
		this.users = users;
	}

	
	
}
