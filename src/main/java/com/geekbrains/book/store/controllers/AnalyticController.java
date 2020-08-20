package com.geekbrains.book.store.controllers;

import com.geekbrains.book.store.entities.MethodStat;
import com.geekbrains.book.store.services.analytic.MethodStatService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/analytic")
@AllArgsConstructor
public class AnalyticController {
    private MethodStatService methodStatService;

    @GetMapping
    public String cartAll(Model model
    ) {
        model.addAttribute("items", methodStatService.getAll());
        return "analytic-page";
    }

}
