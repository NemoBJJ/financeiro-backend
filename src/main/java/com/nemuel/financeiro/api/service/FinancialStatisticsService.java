package com.nemuel.financeiro.api.service;

import com.nemuel.financeiro.api.entity.Transaction;
import com.nemuel.financeiro.api.repository.TransactionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FinancialStatisticsService {

    private final TransactionRepository transactionRepository;

    public FinancialStatisticsService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    // Operações CRUD Básicas
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction addTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public String deleteTransaction(Long id) {
        if (transactionRepository.existsById(id)) {
            transactionRepository.deleteById(id);
            return "Transaction removed successfully!";
        }
        return "Transaction not found!";
    }

    public Page<Transaction> getTransactionsPaged(Pageable pageable) {
        return transactionRepository.findAll(pageable);
    }

    // Método de Cálculo de Estatísticas
    public FinancialStatistics calculateStatistics() {
        List<Transaction> transactions = transactionRepository.findAll();

        List<Double> receitas = transactions.stream()
                .filter(t -> "RECEITA".equalsIgnoreCase(t.getType()))
                .map(Transaction::getAmount)
                .toList();

        List<Double> despesas = transactions.stream()
                .filter(t -> "DESPESA".equalsIgnoreCase(t.getType()))
                .map(Transaction::getAmount)
                .toList();

        double totalReceitas = roundToTwoDecimalPlaces(receitas.stream().mapToDouble(Double::doubleValue).sum());
        double totalDespesas = roundToTwoDecimalPlaces(despesas.stream().mapToDouble(Double::doubleValue).sum());
        double mediaReceitasMensal = roundToTwoDecimalPlaces(totalReceitas / 12);
        double mediaDespesasMensal = roundToTwoDecimalPlaces(totalDespesas / 12);
        double medianaReceitas = roundToTwoDecimalPlaces(calculateMediana(receitas));
        double medianaDespesas = roundToTwoDecimalPlaces(calculateMediana(despesas));
        double modaReceitas = roundToTwoDecimalPlaces(calculateModa(receitas));
        double modaDespesas = roundToTwoDecimalPlaces(calculateModa(despesas));
        double desvioPadraoReceitas = roundToTwoDecimalPlaces(calculateDesvioPadrao(receitas));
        double desvioPadraoDespesas = roundToTwoDecimalPlaces(calculateDesvioPadrao(despesas));

        return new FinancialStatistics(totalReceitas, totalDespesas, mediaReceitasMensal, mediaDespesasMensal,
                medianaReceitas, medianaDespesas, modaReceitas, modaDespesas, desvioPadraoReceitas, desvioPadraoDespesas);
    }

    // Métodos Auxiliares para Cálculos
    private double calculateMediana(List<Double> valores) {
        if (valores.isEmpty()) return 0;
        List<Double> sorted = valores.stream().sorted().toList();
        int middle = sorted.size() / 2;
        if (sorted.size() % 2 == 0) {
            return (sorted.get(middle - 1) + sorted.get(middle)) / 2.0;
        } else {
            return sorted.get(middle);
        }
    }

    private double calculateModa(List<Double> valores) {
        if (valores.isEmpty()) return 0;
        return valores.stream()
                .collect(Collectors.groupingBy(v -> v, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow()
                .getKey();
    }

    private double calculateDesvioPadrao(List<Double> valores) {
        if (valores.isEmpty()) return 0;
        double media = valores.stream().mapToDouble(Double::doubleValue).average().orElse(0);
        return Math.sqrt(valores.stream().mapToDouble(v -> Math.pow(v - media, 2)).average().orElse(0));
    }

    private double roundToTwoDecimalPlaces(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
