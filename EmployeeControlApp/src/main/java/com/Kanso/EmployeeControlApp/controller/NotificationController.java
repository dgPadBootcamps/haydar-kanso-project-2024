package com.Kanso.EmployeeControlApp.controller;


import com.Kanso.EmployeeControlApp.service.NotificationService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/notifications")
    public String viewNotifications(Model model, Authentication authentication) {
        String username = authentication.getName();
        String notifications = notificationService.getNotificationsForUser(username);
        model.addAttribute("notifications", notifications);
        return "notifications";
    }
}