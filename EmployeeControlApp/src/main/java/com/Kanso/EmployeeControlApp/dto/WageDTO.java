package com.Kanso.EmployeeControlApp.dto;



public class WageDTO {
    private Long id;
    private String username;
    private Double amount;
    private String period;

    // Constructors
    public WageDTO() {}

    public WageDTO(Long id, String username, Double amount, String period) {
        this.id = id;
        this.username = username;
        this.amount = amount;
        this.period = period;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public String getPeriod() { return period; }
    public void setPeriod(String period) { this.period = period; }
}