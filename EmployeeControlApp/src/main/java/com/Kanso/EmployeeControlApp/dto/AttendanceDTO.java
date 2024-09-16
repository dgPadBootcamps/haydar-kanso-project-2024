package com.Kanso.EmployeeControlApp.dto;


import java.time.LocalDateTime;

public class AttendanceDTO {
    private Long id;
    private String username;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;

    // Constructors
    public AttendanceDTO() {}

    public AttendanceDTO(Long id, String username, LocalDateTime checkInTime, LocalDateTime checkOutTime) {
        this.id = id;
        this.username = username;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public LocalDateTime getCheckInTime() { return checkInTime; }
    public void setCheckInTime(LocalDateTime checkInTime) { this.checkInTime = checkInTime; }

    public LocalDateTime getCheckOutTime() { return checkOutTime; }
    public void setCheckOutTime(LocalDateTime checkOutTime) { this.checkOutTime = checkOutTime; }
}