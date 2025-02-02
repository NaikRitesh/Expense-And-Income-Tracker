package com.example.ExpenseTracker.services.income;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.ExpenseTracker.dto.IncomeDTO;
import com.example.ExpenseTracker.entity.Expense;
import com.example.ExpenseTracker.entity.Income;
import com.example.ExpenseTracker.repository.IncomeRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IncomeServiceImpl implements IncomeService   {
	
	
	
	private final IncomeRepository incomeRepository;
	
	public Income postIncome(IncomeDTO incomeDTO) {
		
		return saveOrUpdateIncome(new Income(), incomeDTO);
	}
	
	private Income saveOrUpdateIncome(Income income,IncomeDTO incomeDTO) {
		
		income.setTitle(incomeDTO.getTitle());
		income.setAmount(incomeDTO.getAmount());
		income.setCategory(incomeDTO.getCategory());
		income.setDate(incomeDTO.getDate());
		income.setDescription(incomeDTO.getDescription());
		
		return incomeRepository.save(income);
	}
	
	public Income updateIncome(Long id,IncomeDTO incomeDTO) {
		Optional<Income> optionalIncome = incomeRepository.findById(id);
		if(optionalIncome.isPresent()) {
			return saveOrUpdateIncome(optionalIncome.get(), incomeDTO);
		}else {
			throw new EntityNotFoundException("Income is not found with id "+id);
		}
	}
	
	public List<IncomeDTO> getAllIncome(){
		return incomeRepository.findAll().stream()
				.sorted(Comparator.comparing(Income::getDate).reversed())
				.map(Income::getIncomeDto)
				.collect(Collectors.toList());
	}
	
	public IncomeDTO getIncomeById(Long id) {
		Optional<Income> optionalIncome = incomeRepository.findById(id);
		if(optionalIncome.isPresent()) {
			return optionalIncome.get().getIncomeDto();
		}else {
			throw new EntityNotFoundException("Income is not present with id "+id);
		}
	}
	
	public void deleteIncome(Long id) {
		Optional<Income> optionalIncome = incomeRepository.findById(id);
		if(optionalIncome.isPresent()) {
			incomeRepository.deleteById(id);
		}else {
			throw new EntityNotFoundException("Income is not present with id "+ id);
		}
	}

}
