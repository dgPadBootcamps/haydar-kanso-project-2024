package com.Kanso.EmployeeControlApp.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.Kanso.EmployeeControlApp.model.Notification;
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    // Custom queries can be added here if needed
}