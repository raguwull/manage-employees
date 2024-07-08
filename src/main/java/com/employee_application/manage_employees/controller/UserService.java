package com.employee_application.manage_employees.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JdbcUserDetailsManager userDetailsManager;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void createUsers(String username, String password, String role) {
    	
    	MyUser newUser = new MyUser();
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setRole(role);

        userRepository.save(newUser);
        
        UserDetails userDetails =
                User.withUsername(username)
                        .password(passwordEncoder.encode(password))
                        .roles(role)
                        .build();

        userDetailsManager.createUser(userDetails);

        // Add more users as needed
    }
}
