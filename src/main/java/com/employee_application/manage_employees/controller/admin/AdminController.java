package com.employee_application.manage_employees.controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.employee_application.manage_employees.model.myuser.MyUser;
import com.employee_application.manage_employees.service.dashboard.DashboardService;
import com.employee_application.manage_employees.service.myuser.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DashboardService dashboardService;

    @GetMapping("/home")
    public String adminHome(Model model) {
    	MyUser user = userService.currentUser();
    	model.addAttribute("user", user);
    	model.addAttribute("totalUsers", dashboardService.getTotalUsers());
    	model.addAttribute("pendingRequests", dashboardService.getPendingRequests());
    	model.addAttribute("systemAlerts", dashboardService.getSystemAlerts());
        return "adminHome";
    }
    
	@GetMapping("/users")
    public String manageUsers(@RequestParam(value = "searchname", required = false) String searchname, Model model) {
        List<MyUser> users = new ArrayList<>();
        try {
        	if (searchname != null && !searchname.isEmpty()) {
            	users.add(userService.getUserByUsername(searchname));
            } else {
                users = userService.getAllUsers();
            }
            model.addAttribute("users", users);
            return "manageUsers"; 
            
        }catch(UsernameNotFoundException ex) {
        	model.addAttribute("user", userService.currentUser());
        	model.addAttribute("errorMessage", "Cannot find user with username " + searchname);
        	return "manageUsers";
        }
        
    }

	@GetMapping("/edituser/{editname}")
    public String editUser(@PathVariable String editname, Model model) {
        model.addAttribute("user", userService.getUserByUsername(editname));
        return "settings-page";
    }
	 

	@GetMapping("/settings")
    public String editMyProfile(Model model) {
		try {
            model.addAttribute("user", userService.currentUser());
            return "settings-page"; 
        } catch (UsernameNotFoundException ex) {
            model.addAttribute("user", userService.currentUser()); 
            model.addAttribute("errorMessage", "Cannot find user");
            return "redirect:admin/home";
        }
    }
	
	@PostMapping("/settings")
    public String updateUser(@ModelAttribute("user") MyUser user, RedirectAttributes redirectAttributes, Model model) {
        userService.updateUser(user);
        redirectAttributes.addFlashAttribute("message", "User updated successfully!");
        model.addAttribute("user", userService.currentUser());
        return "redirect:/admin/users";
    }

	@GetMapping("/deleteuser/{deletename}")
    public String deleteUser(@PathVariable String deletename, RedirectAttributes redirectAttributes) {
		if (userService.currentUser().getUsername().equals(deletename)) {
			userService.deleteUser(deletename);
	        redirectAttributes.addFlashAttribute("message", "User deleted successfully!");
	        return "redirect:/logout";
		}else {
			userService.deleteUser(deletename);
	        redirectAttributes.addFlashAttribute("message", "User deleted successfully!");
	        return "redirect:/admin/users";
		}
    }
}