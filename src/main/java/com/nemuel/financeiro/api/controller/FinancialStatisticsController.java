package com.nemuel.financeiro.api.controller;

import com.nemuel.financeiro.api.service.FinancialStatisticsService; // Corrigido o import correto
import com.nemuel.financeiro.api.service.FinancialStatistics;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard/statistics")
public class FinancialStatisticsController {

    private final FinancialStatisticsService statisticsService; // Instância correta do serviço

    // Construtor corrigido para injeção de dependência
    public FinancialStatisticsController(FinancialStatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    // Endpoint para retornar as estatísticas
    @GetMapping
    public FinancialStatistics getStatistics() {
        return statisticsService.calculateStatistics();
    }
}
