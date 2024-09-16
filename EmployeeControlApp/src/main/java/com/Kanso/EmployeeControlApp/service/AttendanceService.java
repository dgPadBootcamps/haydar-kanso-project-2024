package com.Kanso.EmployeeControlApp.service;

import com.Kanso.EmployeeControlApp.model.Attendance;
import com.Kanso.EmployeeControlApp.model.User;
import com.Kanso.EmployeeControlApp.repository.AttendanceRepository;
import com.Kanso.EmployeeControlApp.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(AttendanceService.class);

    public AttendanceService(AttendanceRepository attendanceRepository, UserRepository userRepository) {
        this.attendanceRepository = attendanceRepository;
        this.userRepository = userRepository;
    }

  /*  public void checkIn(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Attendance attendance = new Attendance();
        attendance.setUser(user);
        attendance.setCheckInTime(LocalDateTime.now());

        attendanceRepository.save(attendance);
        logger.info("User {} has successfully checked in.", username);
    }*/
  @Transactional
  public void checkIn(String username) {
      User user = userRepository.findByUsername(username)
              .orElseThrow(() -> new UsernameNotFoundException("User not found"));

      // Check if there's already an ongoing check-in record
      Attendance latestAttendance = attendanceRepository.findLatestByUser(user)
              .orElse(null);

      if (latestAttendance != null && latestAttendance.getCheckOutTime() == null) {
          throw new IllegalStateException("User is already checked in.");
      } else {
          Attendance attendance = new Attendance();
          attendance.setUser(user);
          attendance.setCheckInTime(LocalDateTime.now());
          attendanceRepository.save(attendance);
      }
  }

/*
   public void checkOut(String username) {
        // Fetch the user from the database
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Try to find the user's attendance for today
        Optional<Attendance> attendanceOptional = findTodayAttendanceRecord(user);

        if (attendanceOptional.isPresent()) {
            Attendance attendance = attendanceOptional.get();
            logger.info("Found attendance record for user {}: {}", username, attendance);

            // Ensure user hasn't already checked out
            if (attendance.getCheckOutTime() == null) {
                attendance.setCheckOutTime(LocalDateTime.now());
                attendanceRepository.save(attendance); // Save the updated attendance record
                logger.info("User {} has successfully checked out.", username);
            } else {
                logger.warn("User {} has already checked out today.", username);
                throw new RuntimeException("User " + username + " has already checked out today.");
            }
        } else {
            logger.error("No check-in record found for user {} today.", username);
            throw new RuntimeException("No check-in record found for user " + username + " today.");
        }
    } */
@Transactional
public void checkOut(String username) {
    User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    // Fetch the latest attendance record
    Attendance latestAttendance = attendanceRepository.findLatestByUser(user)
            .orElse(null);

    if (latestAttendance != null && latestAttendance.getCheckOutTime() == null) {
        latestAttendance.setCheckOutTime(LocalDateTime.now());
        attendanceRepository.save(latestAttendance);
    } else {
        throw new IllegalStateException("No check-in record found or already checked out.");
    }
}

    private Optional<Attendance> findTodayAttendanceRecord(User user) {
        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime endOfDay = LocalDate.now().atTime(23, 59, 59);

        // Find the attendance record between start and end of today for this user
        return attendanceRepository.findByUserAndDate(user, startOfDay, endOfDay);
    }

    public List<Attendance> getAttendanceForUser(String username) {
        return attendanceRepository.findByUser_Username(username); // Use repository to fetch records by username
    }

    @Transactional
    public void resetAllAttendance() {
        attendanceRepository.deleteAll();
    }
}


