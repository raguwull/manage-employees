package com.employee_application.manage_employees.controller.failure;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/access-denied")
public class FailureController {

    @GetMapping
    public String accessDenied(Model model) {
    	model.addAttribute("errorMessage", "You do not have permission to access this page");
        return "accessDenied";
    }
}
