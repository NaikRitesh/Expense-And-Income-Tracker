package com.example.ExpenseTracker.dto;

import java.util.List;

import com.example.ExpenseTracker.entity.Expense;
import com.example.ExpenseTracker.entity.Income;

import lombok.Data;

@Data
public class GraphDTO {

	private List<Expense> expenseList;
	private List<Income> incomeList;
	
	
	
	
}
