package com.example.ExpenseTracker.services.stats;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

import org.springframework.stereotype.Service;

import com.example.ExpenseTracker.dto.GraphDTO;
import com.example.ExpenseTracker.dto.StatsDTO;
import com.example.ExpenseTracker.entity.Expense;
import com.example.ExpenseTracker.entity.Income;
import com.example.ExpenseTracker.repository.ExpenseRepository;
import com.example.ExpenseTracker.repository.IncomeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService{

	private final IncomeRepository incomeRepository;
	
	private final ExpenseRepository expenseRepository;
	
	public GraphDTO getCharData() {
		LocalDate endDate = LocalDate.now();
		LocalDate startDate = endDate.minusDays(27);
		
		GraphDTO graphDTO = new GraphDTO();
		graphDTO.setExpenseList(expenseRepository.findByDateBetween(startDate, endDate));
		graphDTO.setIncomeList(incomeRepository.findByDateBetween(startDate, endDate));
		
		return graphDTO;
	}
	
	public StatsDTO getStats() {
		
		Double totalIncome = incomeRepository.sumAllAmount();
		Double totalExpense = expenseRepository.sumAllAmount();
		
		Optional<Expense> optionalExpense = expenseRepository.findFirstByOrderByDateDesc();
		Optional<Income> optionalIncome = incomeRepository.findFirstByOrderByDateDesc();
		
		StatsDTO statsDTO = new StatsDTO();
		statsDTO.setExpense(totalExpense);
		statsDTO.setIncome(totalIncome);
		
		if(optionalExpense.isPresent()) {
			statsDTO.setLatestExpense(optionalExpense.get());
		}
		if(optionalIncome.isPresent()) {
			statsDTO.setLatestIncome(optionalIncome.get());
		}
		
		statsDTO.setBalance(totalIncome-totalExpense);
		
		List<Income> incomeList = incomeRepository.findAll();
		List<Expense> expenseList = expenseRepository.findAll();
		
		OptionalDouble minIncome = incomeList.stream().mapToDouble(Income :: getAmount).min();
		OptionalDouble maxIncome = incomeList.stream().mapToDouble(Income :: getAmount).max();
		
		OptionalDouble minExpense = expenseList.stream().mapToDouble(Expense :: getAmount).min();
		OptionalDouble maxExpense = expenseList.stream().mapToDouble(Expense :: getAmount).max();
		
		statsDTO.setMaxExpense(maxExpense.isPresent() ? maxExpense.getAsDouble(): null);
		statsDTO.setMinExpense(minExpense.isPresent() ? minExpense.getAsDouble(): null);
		
		statsDTO.setMaxIncome(maxIncome.isPresent() ? maxIncome.getAsDouble(): null);
		statsDTO.setMinIncome(minIncome.isPresent() ? minIncome.getAsDouble(): null);
		
		
		return statsDTO;
	}
	
	
}
