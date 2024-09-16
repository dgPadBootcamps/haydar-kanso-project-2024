package com.Kanso.EmployeeControlApp.repository;

import com.Kanso.EmployeeControlApp.model.Attendance;
import com.Kanso.EmployeeControlApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    @Query("SELECT a FROM Attendance a WHERE a.user = :user AND a.checkInTime BETWEEN :startOfDay AND :endOfDay")
    Optional<Attendance> findByUserAndDate(
            @Param("user") User user,
            @Param("startOfDay") LocalDateTime startOfDay,
            @Param("endOfDay") LocalDateTime endOfDay
    );
    // New method to find all attendance records by user's username
    @Query("SELECT a FROM Attendance a WHERE a.user.username = :username")
    List<Attendance> findByUser_Username(@Param("username") String username);

    // Find the most recent attendance record for the user, ordered by check-in time in descending order
    @Query("SELECT a FROM Attendance a WHERE a.user = :user ORDER BY a.checkInTime DESC")
    Optional<Attendance> findLatestByUser(@Param("user") User user);
}