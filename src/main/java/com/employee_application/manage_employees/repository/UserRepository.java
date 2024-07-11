package com.employee_application.manage_employees.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee_application.manage_employees.model.myuser.MyUser;


public interface UserRepository extends JpaRepository<MyUser, Long> {
    Optional<MyUser> findByUsername(String username);
}