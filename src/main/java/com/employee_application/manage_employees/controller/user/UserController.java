package com.employee_application.manage_employees.controller.user;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.employee_application.manage_employees.model.myuser.MyUser;
import com.employee_application.manage_employees.model.project.Project;
import com.employee_application.manage_employees.service.myuser.UserService;
import com.employee_application.manage_employees.service.project.ProjectService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired 
	private UserService userService;
	
	@Autowired
	private ProjectService projectService;
	
    @GetMapping("/home")
    public String userHome(Model model) {
    	List<Project> projects = projectService.getProjects(userService.currentUser().getUsername());
    	model.addAttribute("user", userService.currentUser());
    	model.addAttribute("projects", projects);
        return "userHome";
    }
        
    @GetMapping("/settings")
    public String editMyProfile(Model model) {
		try {
            model.addAttribute("user", userService.currentUser());
            return "settings-page"; 
        } catch (UsernameNotFoundException ex) {
            model.addAttribute("user", userService.currentUser()); 
            model.addAttribute("errorMessage", "Cannot find user");
            return "redirect:/user/home";
        }
    }
    
    @PostMapping("/settings")
    public String updateUser(@ModelAttribute("user") MyUser user, RedirectAttributes redirectAttributes, Model model) {
        userService.updateUser(user);
        redirectAttributes.addFlashAttribute("message", "User updated successfully!");
        model.addAttribute("user", userService.currentUser());
        return "redirect:/user/home";
    }
    
    @GetMapping("/projects")
    public String getProjects(Model model) {
    	List<Project> projects = projectService.getProjects(userService.currentUser().getUsername());
    	model.addAttribute("user", userService.currentUser());
    	model.addAttribute("projects", projects);
    	return "userHome";
    }
    
    @GetMapping("/projects/{status}")
    @ResponseBody
    public List<Project> getProjectsStatus(@PathVariable String status) {
    	if (status.equals("All")) {
    		return projectService.getProjects(userService.currentUser().getUsername());
    	}
        return projectService.getProjectByStatus(userService.currentUser().getUsername(), status);
    }

    
}