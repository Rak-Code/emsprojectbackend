package com.laitusneo.backend.dto;

public class AttendanceResponseDTO {
    private Long attendanceId;
    private Long employeeId;
    private String employeeName;
    private String date;
    private String punchInTime;
    private String punchOutTime;
    private Double totalHours;
    private String status;

    // Constructors
    public AttendanceResponseDTO() {}

    public AttendanceResponseDTO(Long attendanceId, Long employeeId, String employeeName, String date, String punchInTime, String punchOutTime, Double totalHours, String status) {
        this.attendanceId = attendanceId;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.date = date;
        this.punchInTime = punchInTime;
        this.punchOutTime = punchOutTime;
        this.totalHours = totalHours;
        this.status = status;
    }

    // Getters and Setters
    public Long getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(Long attendanceId) {
        this.attendanceId = attendanceId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPunchInTime() {
        return punchInTime;
    }

    public void setPunchInTime(String punchInTime) {
        this.punchInTime = punchInTime;
    }

    public String getPunchOutTime() {
        return punchOutTime;
    }

    public void setPunchOutTime(String punchOutTime) {
        this.punchOutTime = punchOutTime;
    }

    public Double getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(Double totalHours) {
        this.totalHours = totalHours;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Builder pattern method
    public static AttendanceResponseDTOBuilder builder() {
        return new AttendanceResponseDTOBuilder();
    }

    // Builder class
    public static class AttendanceResponseDTOBuilder {
        private Long attendanceId;
        private Long employeeId;
        private String employeeName;
        private String date;
        private String punchInTime;
        private String punchOutTime;
        private Double totalHours;
        private String status;

        public AttendanceResponseDTOBuilder attendanceId(Long attendanceId) {
            this.attendanceId = attendanceId;
            return this;
        }

        public AttendanceResponseDTOBuilder employeeId(Long employeeId) {
            this.employeeId = employeeId;
            return this;
        }

        public AttendanceResponseDTOBuilder employeeName(String employeeName) {
            this.employeeName = employeeName;
            return this;
        }

        public AttendanceResponseDTOBuilder date(String date) {
            this.date = date;
            return this;
        }

        public AttendanceResponseDTOBuilder punchInTime(String punchInTime) {
            this.punchInTime = punchInTime;
            return this;
        }

        public AttendanceResponseDTOBuilder punchOutTime(String punchOutTime) {
            this.punchOutTime = punchOutTime;
            return this;
        }

        public AttendanceResponseDTOBuilder totalHours(Double totalHours) {
            this.totalHours = totalHours;
            return this;
        }

        public AttendanceResponseDTOBuilder status(String status) {
            this.status = status;
            return this;
        }

        public AttendanceResponseDTO build() {
            return new AttendanceResponseDTO(attendanceId, employeeId, employeeName, date, punchInTime, punchOutTime, totalHours, status);
        }
    }
}