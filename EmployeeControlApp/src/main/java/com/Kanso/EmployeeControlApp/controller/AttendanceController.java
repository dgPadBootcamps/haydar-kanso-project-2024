package com.Kanso.EmployeeControlApp.controller;


import com.Kanso.EmployeeControlApp.model.Attendance;
import com.Kanso.EmployeeControlApp.model.User;
import com.Kanso.EmployeeControlApp.repository.AttendanceRepository;
import com.Kanso.EmployeeControlApp.repository.UserRepository;
import com.Kanso.EmployeeControlApp.service.AttendanceService;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
@Service
@Controller
@RequestMapping("/attendance")
public class AttendanceController {
    private final AttendanceRepository attendanceRepository;
    private final UserRepository userRepository;
    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceRepository attendanceRepository, UserRepository userRepository, AttendanceService attendanceService) {
        this.attendanceRepository = attendanceRepository;
        this.userRepository = userRepository;
        this.attendanceService = attendanceService;
    }

    @GetMapping
    public String showAttendancePage(Model model) {
        // Get the authenticated user's username
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Fetch the attendance records for the user
        List<Attendance> attendanceList = attendanceService.getAttendanceForUser(username);

        // Add the attendance records to the model
        model.addAttribute("attendanceList", attendanceList);

        return "attendance"; // Thymeleaf template name
    }

    @PostMapping("/checkin")
    public String checkIn(Authentication authentication) {
        String username = authentication.getName(); // Get the current authenticated user's username
        attendanceService.checkIn(username); // Record check-in time
        return "redirect:/attendance"; // Redirect to attendance page

    }



        @PostMapping("/checkout")
        public String checkOut (Authentication authentication){
            String username = authentication.getName();
            attendanceService.checkOut(username); // Record check-out time
            return "redirect:/attendance"; // Redirect to attendance page
        }

        @PostMapping("/reset") // Use POST instead of DELETE
        public String resetAttendance (Model model){
            attendanceService.resetAllAttendance();
            model.addAttribute("resetMessage", "All attendance records have been reset."); // Add message to model
            return "redirect:/attendance"; // Redirect to attendance page or another page as needed
        }
    }
