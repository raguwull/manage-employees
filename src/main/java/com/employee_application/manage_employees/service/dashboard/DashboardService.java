package com.employee_application.manage_employees.service.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee_application.manage_employees.repository.AlertRepository;
import com.employee_application.manage_employees.repository.RequestRepository;
import com.employee_application.manage_employees.repository.UserRepository;

@Service
public class DashboardService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private AlertRepository alertRepository;

    public long getTotalUsers() {
        return userRepository.count();
    }

    public long getPendingRequests() {
        return requestRepository.countByStatus("PENDING");
    }

    public long getSystemAlerts() {
        return alertRepository.countByResolved(false);
    }
}
