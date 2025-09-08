package com.laitusneo.backend.service;

import com.laitusneo.backend.entity.LeaveRequest;
import com.laitusneo.backend.entity.Employee;
import com.laitusneo.backend.repository.LeaveRequestRepository;
import com.laitusneo.backend.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LeaveRequestService {

    private final LeaveRequestRepository leaveRequestRepository;
    private final EmployeeRepository employeeRepository;

    public LeaveRequestService(LeaveRequestRepository leaveRequestRepository, EmployeeRepository employeeRepository) {
        this.leaveRequestRepository = leaveRequestRepository;
        this.employeeRepository = employeeRepository;
    }

    public LeaveRequest applyLeave(LeaveRequest leaveRequest) {
        // Validate leave dates
        if (leaveRequest.getStartDate().isAfter(leaveRequest.getEndDate())) {
            throw new RuntimeException("Start date cannot be after end date");
        }
        if (leaveRequest.getStartDate().isBefore(java.time.LocalDate.now())) {
            throw new RuntimeException("Cannot apply leave for past dates");
        }
        return leaveRequestRepository.save(leaveRequest);
    }

    public List<LeaveRequest> getAllLeaveRequests() {
        return leaveRequestRepository.findAll();
    }

    public LeaveRequest getLeaveRequestById(Long id) {
        return leaveRequestRepository.findById(id).orElse(null);
    }

    public List<LeaveRequest> getLeaveRequestsByEmployee(Employee employee) {
        return leaveRequestRepository.findByEmployee(employee);
    }

    public List<LeaveRequest> getLeaveRequestsByEmployee(Long employeeId) {
        return leaveRequestRepository.findByEmployeeId(employeeId);
    }

    public List<LeaveRequest> getLeaveRequestsByStatus(LeaveRequest.Status status) {
        return leaveRequestRepository.findByStatus(status);
    }

    public LeaveRequest approveLeave(Long id, Long approverId) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(id).orElse(null);
        if (leaveRequest != null) {
            leaveRequest.setStatus(LeaveRequest.Status.APPROVED);
            leaveRequest.setRespondedOn(LocalDateTime.now());
            Employee approver = employeeRepository.findById(approverId).orElse(null);
            leaveRequest.setApprovedBy(approver);
            return leaveRequestRepository.save(leaveRequest);
        }
        return null;
    }

    public LeaveRequest rejectLeave(Long id, Long approverId) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(id).orElse(null);
        if (leaveRequest != null) {
            leaveRequest.setStatus(LeaveRequest.Status.REJECTED);
            leaveRequest.setRespondedOn(LocalDateTime.now());
            Employee approver = employeeRepository.findById(approverId).orElse(null);
            leaveRequest.setApprovedBy(approver);
            return leaveRequestRepository.save(leaveRequest);
        }
        return null;
    }

    public LeaveRequest updateLeaveRequest(LeaveRequest leaveRequest) {
        return leaveRequestRepository.save(leaveRequest);
    }

    public void deleteLeaveRequest(Long id) {
        leaveRequestRepository.deleteById(id);
    }
}
