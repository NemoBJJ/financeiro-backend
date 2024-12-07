package com.nemuel.financeiro.api.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class DashboardController {

    @CrossOrigin(origins = "http://localhost:3000") // Permite apenas o frontend acessar
    @GetMapping("/dashboard")
    public Map<String, Object> getDashboardData() {
        Map<String, Object> data = new HashMap<>();
        data.put("message", "Bem-vindo ao Dashboard");
        data.put("user", "Nemuel");
        data.put("balance", 1000.50);
        return data;
    }
}
