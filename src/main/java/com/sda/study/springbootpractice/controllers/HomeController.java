package com.sda.study.springbootpractice.controllers;

import com.sda.study.springbootpractice.exceptions.SchoolNotFoundException;
import com.sda.study.springbootpractice.services.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Tiina Raudla
 * @Date
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping()
    public String showHomePage() {
        return "home";

    }
}
