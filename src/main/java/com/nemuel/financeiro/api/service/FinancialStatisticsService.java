package com.nemuel.financeiro.api.service;

import com.nemuel.financeiro.api.entity.Transaction;
import com.nemuel.financeiro.api.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FinancialStatisticsService {

    private final TransactionRepository transactionRepository;

    public FinancialStatisticsService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public FinancialStatistics calculateStatistics() {
        // Obtenha todas as transações
        List<Transaction> transactions = transactionRepository.findAll();

        // Filtrar receitas e despesas
        List<Double> receitas = transactions.stream()
                .filter(t -> "RECEITA".equals(t.getType()))
                .map(Transaction::getAmount)
                .collect(Collectors.toList());

        List<Double> despesas = transactions.stream()
                .filter(t -> "DESPESA".equals(t.getType()))
                .map(Transaction::getAmount)
                .collect(Collectors.toList());

        // Cálculos básicos
        double totalReceitas = roundToTwoDecimalPlaces(receitas.stream().mapToDouble(Double::doubleValue).sum());
        double totalDespesas = roundToTwoDecimalPlaces(despesas.stream().mapToDouble(Double::doubleValue).sum());

        double mediaReceitasMensal = roundToTwoDecimalPlaces(totalReceitas / 12);
        double mediaDespesasMensal = roundToTwoDecimalPlaces(totalDespesas / 12);

        // Cálculos avançados
        double medianaReceitas = roundToTwoDecimalPlaces(calculateMediana(receitas));
        double medianaDespesas = roundToTwoDecimalPlaces(calculateMediana(despesas));

        double modaReceitas = roundToTwoDecimalPlaces(calculateModa(receitas));
        double modaDespesas = roundToTwoDecimalPlaces(calculateModa(despesas));

        double desvioPadraoReceitas = roundToTwoDecimalPlaces(calculateDesvioPadrao(receitas));
        double desvioPadraoDespesas = roundToTwoDecimalPlaces(calculateDesvioPadrao(despesas));

        // Criar e retornar o objeto FinancialStatistics
        return new FinancialStatistics(totalReceitas, totalDespesas, mediaReceitasMensal, mediaDespesasMensal,
                medianaReceitas, medianaDespesas, modaReceitas, modaDespesas,
                desvioPadraoReceitas, desvioPadraoDespesas);
    }

    private double calculateMediana(List<Double> valores) {
        if (valores.isEmpty()) return 0;
        Collections.sort(valores);
        int tamanho = valores.size();
        if (tamanho % 2 == 0) {
            return (valores.get(tamanho / 2 - 1) + valores.get(tamanho / 2)) / 2.0;
        } else {
            return valores.get(tamanho / 2);
        }
    }

    private double calculateModa(List<Double> valores) {
        if (valores.isEmpty()) return 0;
        Map<Double, Long> frequencias = valores.stream()
                .collect(Collectors.groupingBy(v -> v, Collectors.counting()));
        return frequencias.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .get()
                .getKey();
    }

    private double calculateDesvioPadrao(List<Double> valores) {
        if (valores.isEmpty()) return 0;
        double media = valores.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
        double variancia = valores.stream()
                .mapToDouble(v -> Math.pow(v - media, 2))
                .average()
                .orElse(0.0);
        return Math.sqrt(variancia);
    }

    // Método auxiliar para arredondar para 2 casas decimais
    private double roundToTwoDecimalPlaces(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
