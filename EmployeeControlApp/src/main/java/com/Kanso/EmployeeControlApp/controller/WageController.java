package com.Kanso.EmployeeControlApp.controller;

import com.Kanso.EmployeeControlApp.repository.WageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WageController {

    @Autowired
    private WageRepository wageRepository;

    @GetMapping("/wages")
    public String showWages(Model model) {
        model.addAttribute("wages", wageRepository.findAll());
        return "wages";
    }
}