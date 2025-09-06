package com.laitusneo.backend.dto;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeaveRequestDTO {
    private Long employeeId;
    private Long leaveTypeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
}