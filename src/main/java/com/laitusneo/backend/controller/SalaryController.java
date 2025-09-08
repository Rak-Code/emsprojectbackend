package com.laitusneo.backend.controller;

import com.laitusneo.backend.entity.Salary;
import com.laitusneo.backend.service.SalaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salaries")
public class SalaryController {

    private final SalaryService salaryService;

    public SalaryController(SalaryService salaryService) {
        this.salaryService = salaryService;
    }

    // Create Salary Record
    @PostMapping
    public ResponseEntity<Salary> createSalary(@RequestBody Salary salary) {
        return ResponseEntity.ok(salaryService.createSalary(salary));
    }

    // Get All Salaries
    @GetMapping
    public ResponseEntity<List<Salary>> getAllSalaries() {
        return ResponseEntity.ok(salaryService.getAllSalaries());
    }

    // Get Salary by ID
    @GetMapping("/{id}")
    public ResponseEntity<Salary> getSalaryById(@PathVariable Long id) {
        return salaryService.getSalaryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get Salaries by Employee ID
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<Salary>> getSalariesByEmployeeId(@PathVariable Long employeeId) {
        return ResponseEntity.ok(salaryService.getSalariesByEmployeeId(employeeId));
    }

    // Update Salary Record
    @PutMapping("/{id}")
    public ResponseEntity<Salary> updateSalary(@PathVariable Long id, @RequestBody Salary salary) {
        return ResponseEntity.ok(salaryService.updateSalary(id, salary));
    }

    // Deactivate Salary (soft disable)
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Salary> deactivateSalary(@PathVariable Long id) {
        return ResponseEntity.ok(salaryService.deactivateSalary(id));
    }

    // Delete Salary Record
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSalary(@PathVariable Long id) {
        salaryService.deleteSalary(id);
        return ResponseEntity.noContent().build();
    }

    // Calculate salary for employee
    @GetMapping("/calculate/{employeeId}")
    public ResponseEntity<?> calculateSalary(@PathVariable Long employeeId) {
        try {
            List<Salary> salaries = salaryService.getSalariesByEmployeeId(employeeId);
            if (salaries.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            // Return the most recent active salary
            Salary latestSalary = salaries.stream()
                    .filter(s -> s.getIsActive() != null && s.getIsActive())
                    .findFirst()
                    .orElse(salaries.get(0));

            return ResponseEntity.ok(latestSalary);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    // Get active salaries
    @GetMapping("/active")
    public ResponseEntity<List<Salary>> getActiveSalaries() {
        return ResponseEntity.ok(salaryService.getActiveSalaries());
    }
}
