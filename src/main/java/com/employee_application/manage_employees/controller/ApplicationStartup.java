package com.employee_application.manage_employees.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup {

    @Autowired
    private UserService userService;

    @EventListener(ApplicationReadyEvent.class)
    public void initializeUsers() {
        userService.createUsers("admin", "123", "ADMIN");
        userService.createUsers("user", "123", "USER");
        userService.createUsers("rohith", "123", "USER");
    }
}