package com.laitusneo.backend.dto;

import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String departmentName;
    private String roleName;
    private LocalDate joiningDate;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}