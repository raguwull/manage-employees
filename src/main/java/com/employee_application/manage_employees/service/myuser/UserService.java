package com.employee_application.manage_employees.service.myuser;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;

import com.employee_application.manage_employees.model.myuser.MyUser;
import com.employee_application.manage_employees.model.register.RegisterRequest;
import com.employee_application.manage_employees.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private JdbcUserDetailsManager userDetailsManager;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    // Get the currently authenticated user
    public MyUser currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = null;
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            username = userDetails.getUsername();
        }
        return getUserByUsername(username);
    }
    
    // TESTING
    public MyUser createUsers(String username, String password, String role, String name, String dept, 
                            String position, String address, String email, long phone, Date dob, 
                            Date dateOfJoining, double salary, String manager, String gender, 
                            String maritalStatus, String emergencyContact, String nationality, String status) {
    	
    	MyUser newUser = new MyUser();
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setRole(role);
        newUser.setName(name);
        newUser.setDept(dept);
        newUser.setPosition(position);
        newUser.setAddress(address);
        newUser.setEmail(email);
        newUser.setPhone(phone);
        newUser.setDob(dob);
        newUser.setDateOfJoining(dateOfJoining);
        newUser.setSalary(salary);
        newUser.setManager(manager);
        newUser.setGender(gender);
        newUser.setMaritalStatus(maritalStatus);
        newUser.setEmergencyContact(emergencyContact);
        newUser.setNationality(nationality);
        newUser.setStatus(status);

        userRepository.save(newUser);
        
        UserDetails userDetails =
                User.withUsername(username)
                        .password(passwordEncoder.encode(password))
                        .roles(role)
                        .build();

        userDetailsManager.createUser(userDetails);
        
        return newUser;
    }
    
    // Create User
    public void processRegisterRequest(RegisterRequest registerRequest) {

		MyUser newUser = new MyUser();
		newUser.setUsername(registerRequest.getUsername());
		newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		newUser.setRole(registerRequest.getRole());
		newUser.setName(registerRequest.getName());
		newUser.setDept(registerRequest.getDept());
		newUser.setPosition(registerRequest.getPosition());
		newUser.setAddress(registerRequest.getAddress());
		newUser.setEmail(registerRequest.getEmail());
		newUser.setPhone(Long.parseLong(registerRequest.getPhone()));
		newUser.setDob(registerRequest.getDob());
		newUser.setDateOfJoining(registerRequest.getDateOfJoining());
		newUser.setSalary(registerRequest.getSalary());
		newUser.setManager(registerRequest.getManager());
		newUser.setGender(registerRequest.getGender());
		newUser.setMaritalStatus(registerRequest.getMaritalStatus());
		newUser.setEmergencyContact(registerRequest.getEmergencyContact());
		newUser.setNationality(registerRequest.getNationality());
		newUser.setStatus(registerRequest.getStatus());
		
		userRepository.save(newUser);
		
		UserDetails userDetails =
		User.withUsername(newUser.getUsername())
		        .password(newUser.getPassword())
		        .roles(newUser.getRole())
		        .build();
		
		userDetailsManager.createUser(userDetails);
    }
    
    
    // Testing
    public void createUser(MyUser newUser) {
		newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
		userRepository.save(newUser);
		
		UserDetails userDetails =
		User.withUsername(newUser.getUsername())
		        .password(newUser.getPassword())
		        .roles(newUser.getRole())
		        .build();
		
		userDetailsManager.createUser(userDetails);
    }
    
    
    // Read User
    public List<MyUser> getAllUsers() {
		return userRepository.findAll();
	}
 
    // Update User
    public void updateUser(MyUser updateRequest) {
        MyUser existingUser = getUserById(updateRequest.getId());
        if (existingUser == null) {
            throw new UsernameNotFoundException("User not found with ID: " + updateRequest.getId());
        }

        // Update user fields
        if (updateRequest.getUsername() != null) existingUser.setUsername(updateRequest.getUsername());
        if (updateRequest.getRole() != null) existingUser.setRole(updateRequest.getRole());
        if (updateRequest.getName() != null) existingUser.setName(updateRequest.getName());
        if (updateRequest.getDept() != null) existingUser.setDept(updateRequest.getDept());
        if (updateRequest.getPosition() != null) existingUser.setPosition(updateRequest.getPosition());
        if (updateRequest.getAddress() != null) existingUser.setAddress(updateRequest.getAddress());
        if (updateRequest.getEmail() != null) existingUser.setEmail(updateRequest.getEmail());
        if (updateRequest.getPhone() != 0) existingUser.setPhone(updateRequest.getPhone());
        if (updateRequest.getDob() != null) existingUser.setDob(updateRequest.getDob());
        if (updateRequest.getDateOfJoining() != null) existingUser.setDateOfJoining(updateRequest.getDateOfJoining());
        if (updateRequest.getSalary() != 0) existingUser.setSalary(updateRequest.getSalary());
        if (updateRequest.getManager() != null) existingUser.setManager(updateRequest.getManager());
        if (updateRequest.getGender() != null) existingUser.setGender(updateRequest.getGender());
        if (updateRequest.getMaritalStatus() != null) existingUser.setMaritalStatus(updateRequest.getMaritalStatus());
        if (updateRequest.getEmergencyContact() != null) existingUser.setEmergencyContact(updateRequest.getEmergencyContact());
        if (updateRequest.getNationality() != null) existingUser.setNationality(updateRequest.getNationality());
        if (updateRequest.getStatus() != null) existingUser.setStatus(updateRequest.getStatus());

        // Save user
        userRepository.save(existingUser);

        UserDetails userDetails = 
        User.withUsername(existingUser.getUsername())
	          .password(existingUser.getPassword())
	          .authorities("ROLE_" + existingUser.getRole())
	          .build();

        userDetailsManager.updateUser(userDetails);
    }

     
    // Delete User
    public void deleteUser(Long id) {
        MyUser existingUser = getUserById(id);
        
        userRepository.deleteById(id);
        
        userDetailsManager.deleteUser(existingUser.getUsername());
    }
    public void deleteUser(String username) {
        MyUser existingUser = getUserByUsername(username);
        
        userRepository.deleteById(existingUser.getId());
        
        userDetailsManager.deleteUser(existingUser.getUsername());
    }
    
    // Find by ID
    public MyUser getUserById(Long id) {
		return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Cannot find user"));
	}
    
    // Find by user name
    public MyUser getUserByUsername(String username) {
		return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Cannot find user"));
	}

	public void saveUser(MyUser user) {
		userRepository.save(user);
	}

}
