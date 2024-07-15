package com.employee_application.manage_employees.startup;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.employee_application.manage_employees.model.finance.Finance;
import com.employee_application.manage_employees.model.myuser.MyUser;
import com.employee_application.manage_employees.model.project.Project;
import com.employee_application.manage_employees.service.excel.ExcelService;
import com.employee_application.manage_employees.service.finance.FinanceService;
import com.employee_application.manage_employees.service.myuser.UserService;
import com.employee_application.manage_employees.service.project.ProjectService;

@Component
public class ApplicationStartup {

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;
    
    @Autowired 
    private FinanceService financeService;
    
    @Autowired 
    private ExcelService excelService;

    @EventListener(ApplicationReadyEvent.class)
    public void initializeUsersAndProjects() {
        Calendar calendar = Calendar.getInstance();

        calendar.set(1990, Calendar.JANUARY, 1);
        Date dob1 = calendar.getTime();
        calendar.set(2010, Calendar.JANUARY, 1);
        Date doj1 = calendar.getTime();

        calendar.set(1985, Calendar.MARCH, 1);
        Date dob2 = calendar.getTime();
        calendar.set(2015, Calendar.MARCH, 1);
        Date doj2 = calendar.getTime();

        calendar.set(1995, Calendar.MAY, 1);
        Date dob3 = calendar.getTime();
        calendar.set(2020, Calendar.MAY, 1);
        Date doj3 = calendar.getTime();

        MyUser admin = new MyUser("tvscs", "tvscs", "ADMIN", "TVS Credit Services", "Technology", "System Admin",
                "123 Admin St", "admin@tvscredit.com", 1234567890L, dob1, doj1, 90000.0, "CEO", "Male", "Married",
                "9876543210", "American", "Active");

        MyUser user = new MyUser("ragul", "ragul", "USER", "Ragul Balasundaram", "IT", "Intern",
                "456 User St", "ragul@tvscredit.com", 2234567890L, dob2, doj2, 90000000.0, "Intern", "Male",
                "Unmarried", "8876543210", "Canadian", "Active");

        MyUser manager = new MyUser("manager", "manager123", "USER", "Manager One", "Operations",
                "Operations Manager", "789 Manager St", "manager@example.com", 3234567890L, dob3, doj3, 80000.0, "COO",
                "Male", "Divorced", "7876543210", "British", "Active");

        MyUser rohith = new MyUser("rohith", "rohith123", "USER", "Rohith Kumar", "Sales",
                "Sales Executive", "159 Sales St", "rohith@example.com", 4234567890L, dob3, doj3, 55000.0,
                "Sales Manager", "Male", "Unmarried", "6876543210", "Indian", "Active");

        MyUser jane = new MyUser("jane", "jane123", "ADMIN", "Jane Doe", "Marketing",
                "Marketing Specialist", "753 Marketing St", "jane@example.com", 5234567890L, dob2, doj2, 58000.0,
                "Marketing Manager", "Female", "Married", "5876543210", "Australian", "Active");

        // Save users
        userService.createUser(admin);
        userService.createUser(user);
        userService.createUser(manager);
        userService.createUser(rohith);
        userService.createUser(jane);
        
        // Create projects for each user
        Project project1 = createProject(
        	    "Customer Relationship Management System",
        	    "Develop a new CRM system for managing customer interactions and data.",
        	    "Pending",
        	    admin, user, manager);

    	Project project2 = createProject(
    	    "AI-Powered Customer Support Chatbot",
    	    "Implement an AI-powered chatbot for customer support services.",
    	    "Finished",
    	    admin, user, manager);

    	Project project3 = createProject(
    	    "Scalable Cloud Infrastructure for Data Analytics",
    	    "Design and deploy a scalable cloud infrastructure for data analytics.",
    	    "Finished",
    	    admin, user, manager);

    	Project project4 = createProject(
    	    "Mobile Field Service Operations Tracker",
    	    "Create a mobile app for real-time tracking of field service operations.",
    	    "Finished",
    	    admin, user, manager);

    	Project project5 = createProject(
    	    "Personalized E-commerce Platform",
    	    "Develop an e-commerce platform with personalized shopping recommendations.",
    	    "Finished",
    	    rohith, jane);

    	Project project6 = createProject(
    	    "Predictive Maintenance with Machine Learning",
    	    "Implement a machine learning model for predictive maintenance in manufacturing.",
    	    "Finished",
    	    rohith, jane);

    	Project project7 = createProject(
    	    "Blockchain-based Supply Chain Management",
    	    "Design and deploy a blockchain-based supply chain management solution.",
    	    "Pending",
    	    rohith, jane);

    	Project project8 = createProject(
    	    "Healthcare Data Management System",
    	    "Develop a healthcare data management system compliant with HIPAA regulations.",
    	    "Finished",
    	    rohith, jane);

    	Project project9 = createProject(
    	    "Virtual Reality Training Simulator for Emergency Response",
    	    "Build an interactive virtual reality training simulator for emergency response teams.",
    	    "Finished",
    	    admin, rohith);

    	Project project10 = createProject(
    	    "Social Media Analytics Tool",
    	    "Create a social media analytics tool for sentiment analysis and engagement metrics.",
    	    "Finished",
    	    user, jane);

    	Project project11 = createProject(
    	    "Content Management System",
    	    "Develop a content management system for publishing and managing digital content.",
    	    "Pending",
    	    manager, jane);

    	Project project12 = createProject(
    	    "Cybersecurity Incident Response Platform",
    	    "Implement a cybersecurity incident response platform with automated threat detection.",
    	    "Finished",
    	    rohith, manager);

        // Save projects
        projectService.saveProject(project1);
        projectService.saveProject(project2);
        projectService.saveProject(project3);
        projectService.saveProject(project4);
        projectService.saveProject(project5);
        projectService.saveProject(project6);
        projectService.saveProject(project7);
        projectService.saveProject(project8);
        projectService.saveProject(project9);
        projectService.saveProject(project10);
        projectService.saveProject(project11);
        projectService.saveProject(project12);

        // Update users with projects
        admin.setProjects(Arrays.asList(project1, project2, project3, project4, project9));
        user.setProjects(Arrays.asList(project1, project2, project3, project4, project10));
        manager.setProjects(Arrays.asList(project1, project2, project3, project4, project11));
        rohith.setProjects(Arrays.asList(project5, project6, project7, project8, project9, project12));
        jane.setProjects(Arrays.asList(project5, project6, project7, project8, project10, project11));

        // Save users
        userService.saveUser(admin);
        userService.saveUser(user);
        userService.saveUser(manager);
        userService.saveUser(rohith);
        userService.saveUser(jane);
        
        
        // Finance
//        Finance finance1 = createFinance(project1, dob1, "Office Supplies", "Purchased office stationery", 150.75, "Office Depot", "Credit Card", 1001, "Approved");
//        Finance finance2 = createFinance(project2, dob2, "Travel", "Team travel to client site", 1200.00, "Travel Agency", "Bank Transfer", 1002, "Pending");
//        Finance finance3 = createFinance(project3, dob3, "Software Licenses", "Annual subscription for project management software", 3500.00, "Software Vendor", "Credit Card", 1003, "Approved");
//        
//        
//        financeService.saveFinance(finance1);
//        financeService.saveFinance(finance2);
//        financeService.saveFinance(finance3);
//        
//        
//        List<Finance> financeList = Arrays.asList(finance1, finance2, finance3);

//        excelService.initialiseSheet();
        
        List<Finance> financeList = excelService.getAllFinaceRecords();
        financeService.saveFinances(financeList);
//        List<Finance> newList = excelService.getAllFinaceRecords();
//        for (Finance f: newList) {
//        	System.out.println(f.getDescription());
//        }
        
    }

    private Project createProject(String name, String description, String status, MyUser... users) {
        Project project = new Project();
        project.setName(name);
        project.setDescription(description);
        project.setStartDate(new Date());
        project.setDuration(12);
        project.setStatus(status);
        project.setUsers(Arrays.asList(users));
        return project;
    }
    
    private Finance createFinance(Project project, Date date, String expenseType, String description, double amount, String paidTo, String paymentMethod, long invoiceNumber, String approvalStatus) {
        Finance finance = new Finance();
        finance.setProject(project);
        finance.setDate(date);
        finance.setExpenseType(expenseType);
        finance.setDescription(description);
        finance.setAmount(amount);
        finance.setPaidTo(paidTo);
        finance.setPaymentMethod(paymentMethod);
        finance.setInvoiceNumber(invoiceNumber);
        finance.setApprovalStatus(approvalStatus);
        return finance;
    }
}
