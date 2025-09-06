package com.laitusneo.backend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceResponseDTO {
    private Long attendanceId;
    private Long employeeId;
    private String employeeName;
    private String date;
    private String punchInTime;
    private String punchOutTime;
    private Double totalHours;
    private String status;
}