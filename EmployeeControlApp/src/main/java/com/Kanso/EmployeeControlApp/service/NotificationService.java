package com.Kanso.EmployeeControlApp.service;


import org.springframework.stereotype.Service;

@Service
public class NotificationService {


    public String getNotificationsForUser(String username) {

        return "List of notifications for user: " + username;
    }


    public void sendNotification(String username, String message) {

        System.out.println("Sending notification to " + username + ": " + message);
    }
}