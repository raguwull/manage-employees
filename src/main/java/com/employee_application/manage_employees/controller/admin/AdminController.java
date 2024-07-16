package com.employee_application.manage_employees.controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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

import com.employee_application.manage_employees.exception.ResourceNotFoundException;
import com.employee_application.manage_employees.model.finance.Finance;
import com.employee_application.manage_employees.model.myuser.MyUser;
import com.employee_application.manage_employees.model.project.Project;
import com.employee_application.manage_employees.service.dashboard.DashboardService;
import com.employee_application.manage_employees.service.excel.ExcelService;
import com.employee_application.manage_employees.service.finance.FinanceService;
import com.employee_application.manage_employees.service.myuser.UserService;
import com.employee_application.manage_employees.service.project.ProjectService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DashboardService dashboardService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private ExcelService excelService;
	
	@Autowired
	private FinanceService financeService;
	
    @GetMapping("/home")
    public String adminHome(Model model) {
    	
    	MyUser user = userService.currentUser();
    	
    	int pendingProjects = projectService.getProjectByStatus("Pending").size();
        int finishedProjects = projectService.getProjectByStatus("Finished").size();

        model.addAttribute("pendingProjects", pendingProjects);
        model.addAttribute("finishedProjects", finishedProjects);
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

	@GetMapping("/projects")
	public String manageProjects(
	        @RequestParam(value = "searchname", required = false) String searchname, 
	        @RequestParam(defaultValue = "0") int page, 
	        Model model) {
	    
	    int pageSize = 6;
	    Page<Project> projectPage;
	    	    
	    try {
	        if (searchname != null && !searchname.isEmpty()) {
	            List<Project> filteredProjects = projectService.getProjectByNameContainingIgnoreCase(searchname);
	            int start = Math.min((int) PageRequest.of(page, pageSize).getOffset(), filteredProjects.size());
	            int end = Math.min((start + PageRequest.of(page, pageSize).getPageSize()), filteredProjects.size());
	            projectPage = new PageImpl<>(filteredProjects.subList(start, end), PageRequest.of(page, pageSize), filteredProjects.size());
	        } else {
	            projectPage = projectService.getPaginatedProjects(PageRequest.of(page, pageSize));
	        }
	        model.addAttribute("projects", projectPage.getContent());
	        model.addAttribute("currentPage", page);
	        model.addAttribute("totalPages", projectPage.getTotalPages());
	        return "manageProjects"; 
	        
	    } catch (ResourceNotFoundException ex) {
	        model.addAttribute("user", userService.currentUser());
	        model.addAttribute("currentPage", page);
	        model.addAttribute("totalPages", 0);
	        model.addAttribute("errorMessage", "Cannot find project");
	        return "manageProjects";
	    } catch (Exception ex) {
	        model.addAttribute("user", userService.currentUser());
	        model.addAttribute("currentPage", page);
	        model.addAttribute("totalPages", 0);
	        model.addAttribute("errorMessage", "Search failed");
	        return "manageProjects";
	    }
	}

	@GetMapping("/finance")
	public String getFinancePage(Model model) {
		model.addAttribute("user", userService.currentUser());		
		model.addAttribute("financeRecords", excelService.getAllFinaceRecords());
		return "manageFinance";
	}
	
	@GetMapping("/finance/create")
	public String getFinanceCreatePage(Model model) {
		model.addAttribute("user", userService.currentUser());		
		model.addAttribute("finance", new Finance());	
		model.addAttribute("projects", projectService.getAllProjects());
		return "addFinance";
	}
	
	@PostMapping("/finance/create")
	public String createFinanceRecord(@ModelAttribute("finance") Finance finance, Model model) {
		financeService.saveFinance(finance);
		excelService.addFinanceRecord(finance);
		model.addAttribute("user", userService.currentUser());		
		return "redirect:/admin/finance";
	}
	
	@GetMapping("/fianance/delete/{id}")
	public String deleteFinance(@PathVariable long id, Model model) {
		financeService.deleteFinanceById(id);
		excelService.deleteFinanceRecord(id);
		return "redirect:/admin/finance";
	}
	
	@GetMapping("/edituser/{editname}")
    public String editUser(@PathVariable String editname, Model model) {
        model.addAttribute("user", userService.getUserByUsername(editname));
        return "settings-page";
    }
	
	@GetMapping("/editproject/{id}")
    public String editProject(@PathVariable long id, Model model) {
        model.addAttribute("project", projectService.getProjectById(id));
        model.addAttribute("user", userService.currentUser());
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
	
	@GetMapping("/deleteproject/{id}")
    public String deleteProject(@PathVariable long id, RedirectAttributes redirectAttributes) {
		projectService.deleteProject(id);
        redirectAttributes.addFlashAttribute("message", "User deleted successfully!");
        return "redirect:/admin/projects";
    }

}