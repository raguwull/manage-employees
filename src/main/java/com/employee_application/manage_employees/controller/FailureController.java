package com.employee_application.manage_employees.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/access-denied")
public class FailureController {

    @GetMapping
    public String accessDenied() {
        return "accessDenied";
    }
}
