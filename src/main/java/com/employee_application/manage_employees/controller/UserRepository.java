package com.employee_application.manage_employees.controller;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.employee_application.manage_employees.controller.MyUser;


public interface UserRepository extends JpaRepository<MyUser, Long> {
    Optional<MyUser> findByUsername(String username);
}
