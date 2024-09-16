package com.Kanso.EmployeeControlApp.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.Kanso.EmployeeControlApp.model.Wage;
public interface WageRepository extends JpaRepository<Wage, Long> {
    // Custom queries can be added here if needed
}