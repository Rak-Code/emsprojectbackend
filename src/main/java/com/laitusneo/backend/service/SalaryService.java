package com.laitusneo.backend.service;

import com.laitusneo.backend.entity.Salary;
import com.laitusneo.backend.repository.SalaryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalaryService {

    private final SalaryRepository salaryRepository;

    public SalaryService(SalaryRepository salaryRepository) {
        this.salaryRepository = salaryRepository;
    }

    public Salary createSalary(Salary salary) {
        // calculate netSalary manually before saving
        salary.setNetSalary(salary.getBaseSalary() + salary.getAllowances() - salary.getDeductions());
        return salaryRepository.save(salary);
    }

    public List<Salary> getAllSalaries() {
        return salaryRepository.findAll();
    }

    public Optional<Salary> getSalaryById(Long id) {
        return salaryRepository.findById(id);
    }

    public List<Salary> getSalariesByEmployeeId(Long employeeId) {
        return salaryRepository.findByEmployeeId(employeeId);
    }

    public List<Salary> getActiveSalaries() {
        return salaryRepository.findByIsActiveTrue();
    }

    public Salary updateSalary(Long id, Salary updatedSalary) {
        Salary salary = salaryRepository.findById(id).orElse(null);
        if (salary != null) {
            if (updatedSalary.getBaseSalary() != null) salary.setBaseSalary(updatedSalary.getBaseSalary());
            if (updatedSalary.getAllowances() != null) salary.setAllowances(updatedSalary.getAllowances());
            if (updatedSalary.getDeductions() != null) salary.setDeductions(updatedSalary.getDeductions());
            if (updatedSalary.getEffectiveDate() != null) salary.setEffectiveDate(updatedSalary.getEffectiveDate());
            if (updatedSalary.getIsActive() != null) salary.setIsActive(updatedSalary.getIsActive());
            
            // Recalculate net salary
            salary.setNetSalary(salary.getBaseSalary() + salary.getAllowances() - salary.getDeductions());
            salary.setUpdatedAt(java.time.LocalDateTime.now());
            return salaryRepository.save(salary);
        }
        return null;
    }

    public Salary deactivateSalary(Long id) {
        Salary salary = salaryRepository.findById(id).orElse(null);
        if (salary != null) {
            salary.setIsActive(false);
            salary.setUpdatedAt(java.time.LocalDateTime.now());
            return salaryRepository.save(salary);
        }
        return null;
    }

    public void deleteSalary(Long id) {
        salaryRepository.deleteById(id);
    }
}
