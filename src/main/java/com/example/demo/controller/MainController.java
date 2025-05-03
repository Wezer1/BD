package com.example.demo.controller;

import com.example.demo.service.DataService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final DataService dataService;

    // Главная страница с меню
    @GetMapping("/")
    public String index() {
        return "index";
    }

    // Справочники
    @GetMapping("/dictionaries")
    public String dictionaries() {
        return "dictionaries";
    }

    // Оперативные данные
    @GetMapping("/data")
    public String data() {
        return "data";
    }

    // Отчеты
    @GetMapping("/reports")
    public String reports() {
        return "reports";
    }

    // О программе
    @GetMapping("/about")
    public String about() {
        return "about";
    }
}
