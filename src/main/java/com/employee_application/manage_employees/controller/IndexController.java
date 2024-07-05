package com.employee_application.manage_employees.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/hello")
    public String customMethod(Model model) {
        model.addAttribute("message", "Hello world");
        return "welcome";    
    }
}