package com.employee_application.manage_employees.startup;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.employee_application.manage_employees.model.myuser.MyUser;
import com.employee_application.manage_employees.model.project.Project;
import com.employee_application.manage_employees.service.myuser.UserService;
import com.employee_application.manage_employees.service.project.ProjectService;

@Component
public class ApplicationStartup {

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

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

        MyUser admin = new MyUser("admin", "admin123", "ADMIN", "Admin User", "Technology", "System Admin",
                "123 Admin St", "admin@example.com", 1234567890L, dob1, doj1, 90000.0, "CEO", "Male", "Married",
                "9876543210", "American", "Active");

        MyUser user = new MyUser("user", "user123", "USER", "User One", "Finance", "Accountant",
                "456 User St", "user@example.com", 2234567890L, dob2, doj2, 60000.0, "Finance Manager", "Female",
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
    	    "Pending",
    	    admin, user, manager);

    	Project project4 = createProject(
    	    "Mobile Field Service Operations Tracker",
    	    "Create a mobile app for real-time tracking of field service operations.",
    	    "Finished",
    	    admin, user, manager);

    	Project project5 = createProject(
    	    "Personalized E-commerce Platform",
    	    "Develop an e-commerce platform with personalized shopping recommendations.",
    	    "Pending",
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
    	    "Pending",
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
}
