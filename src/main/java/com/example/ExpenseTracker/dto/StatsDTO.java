package com.example.ExpenseTracker.dto;

import com.example.ExpenseTracker.entity.Expense;
import com.example.ExpenseTracker.entity.Income;

import lombok.Data;

@Data
public class StatsDTO {

	private double income;
	private double expense;
	private Income latestIncome;
	private Expense latestExpense;
	
	private Double balance;
	private Double minIncome;
	private Double maxIncome;
	private Double minExpense;
	private Double maxExpense;
	
	
}
