//package com.employee_application.manage_employees.controller;
//
//import java.security.Principal;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//@Controller
//public class IndexController {
//	@Autowired
//	UserDetailsService userDetailsService;
//	
//    @GetMapping("/")
//    public String getHomePage(Model model) {
//        return "welcomePage";    
//    }
//    @GetMapping("/login")
//    public String getLoginPage(Model model) {
////    	model.addAttribute("AuthenticationRequest", new AuthenticationRequest());
//        return "loginPage";    
//    }
//    @GetMapping("/register")
//    public String getRegisterPage() {
//        return "registerPage";    
//    }
//
//    @GetMapping("/admin/home")
//    @PreAuthorize("hasRole('ADMIN')")
//    public String adminDashboard(Model model, Principal principal) {
//    	UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
//		model.addAttribute("user", userDetails);
//		return "adminHomePage";
//    }
//
//    @GetMapping("/user/home")
//    @PreAuthorize("hasRole('USER')")
//    public String userDashboard(Model model, Principal principal) {
//    	UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
//		model.addAttribute("user", userDetails);
//		return "userHomePage";
//    }
// 
//    @GetMapping("/error")
//    public String logout() {
//        return "errorPage";
//    }
//}