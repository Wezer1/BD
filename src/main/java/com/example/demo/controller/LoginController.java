package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String Login() {
        return "login"; // Это имя шаблона без расширения (например, login.html)
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }
    @GetMapping("/company")
    public String Company() {
        return "companies"; // src/main/resources/templates/register.html
    }
    @GetMapping("/airplane")
    public String Airplane() {
        return "airplanes"; // src/main/resources/templates/register.html
    }
    @GetMapping("/flight")
    public String Flight() {
        return "flights"; // src/main/resources/templates/register.html
    }
    @GetMapping("/schedule")
    public String Schedule() {
        return "schedules"; // src/main/resources/templates/register.html
    }
    @GetMapping("/users")
    public String Users() {
        return "users"; // src/main/resources/templates/register.html
    }
    @GetMapping("/ticket")
    public String Ticket() {
        return "ticket"; // src/main/resources/templates/register.html
    }
}
