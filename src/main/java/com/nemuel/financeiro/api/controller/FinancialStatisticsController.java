package com.nemuel.financeiro.api.controller;

import com.nemuel.financeiro.api.service.FinancialStatistics;
import com.nemuel.financeiro.api.service.FinancialStatisticsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard/statistics")
public class FinancialStatisticsController {

    private final FinancialStatisticsService statisticsService;

    public FinancialStatisticsController(FinancialStatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping
    public FinancialStatistics getStatistics() {
        return statisticsService.calculateStatistics();
    }
}
