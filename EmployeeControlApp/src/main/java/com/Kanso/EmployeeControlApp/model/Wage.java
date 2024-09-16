package com.Kanso.EmployeeControlApp.model;
import jakarta.persistence.*;
@Entity
public class Wage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private Double amount;
    private String period; // e.g., "monthly", "bi-weekly"

    // Getters and setters

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Double getAmount() {
        return amount;
    }

    public String getPeriod() {
        return period;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
}
