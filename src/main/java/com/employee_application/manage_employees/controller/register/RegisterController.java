package com.employee_application.manage_employees.controller.register;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.employee_application.manage_employees.model.register.RegisterRequest;

@Controller
public class RegisterController {

    @GetMapping("/register")
    public String register(Model model) {
    	RegisterRequest registerRequest = new RegisterRequest();
    	model.addAttribute("user", registerRequest);
        return "registerPage";
    }
}