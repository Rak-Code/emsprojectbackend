package com.laitusneo.backend.dto;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeCreateDTO {
    private Long userId;
    private String name;
    private String email;
    private String phone;
    private String address;
    private Long departmentId;
    private Long roleId;
    private LocalDate joiningDate;
}