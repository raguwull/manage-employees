package com.employee_application.manage_employees.service.finance;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee_application.manage_employees.model.finance.Finance;
import com.employee_application.manage_employees.repository.FinanceRepository;

@Service
public class FinanceService {

	@Autowired 
	private FinanceRepository financeRepository;
	
	public void saveFinance(Finance finance) {
		financeRepository.save(finance);
	}

	public List<Finance> getAllFinances() {
		return financeRepository.findAll();
	}

	public void saveFinances(List<Finance> financeList) {
		financeRepository.saveAll(financeList);
	}

	public void deleteFinanceById(long id) {
		financeRepository.deleteById(id);
	}

}
