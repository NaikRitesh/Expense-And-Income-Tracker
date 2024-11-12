package com.example.ExpenseTracker.services.income;

import java.util.List;

import com.example.ExpenseTracker.dto.IncomeDTO;
import com.example.ExpenseTracker.entity.Income;

public interface IncomeService {
	
	Income postIncome(IncomeDTO incomeDTO);
	List<IncomeDTO> getAllIncome();
	Income updateIncome(Long id,IncomeDTO incomeDTO);
	IncomeDTO getIncomeById(Long id);
	void deleteIncome(Long id);

}
