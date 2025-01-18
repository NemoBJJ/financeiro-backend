package com.nemuel.financeiro.api.controller;

import com.nemuel.financeiro.api.entity.Transaction;
import com.nemuel.financeiro.api.service.FinancialStatisticsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin(origins = "http://localhost:3002")
public class TransactionController {

    private final FinancialStatisticsService financialStatisticsService;

    public TransactionController(FinancialStatisticsService financialStatisticsService) {
        this.financialStatisticsService = financialStatisticsService;
    }

    // Retorna todas as transações do banco de dados
    @GetMapping
    public List<Transaction> getAllTransactions() {
        return financialStatisticsService.getAllTransactions();
    }

    // Adiciona uma nova transação ao banco de dados
    @PostMapping
    public Transaction addTransaction(@RequestBody Transaction transaction) {
        return financialStatisticsService.addTransaction(transaction);
    }

    // Remove uma transação com base no ID fornecido
    @DeleteMapping("/{id}")
    public String deleteTransaction(@PathVariable Long id) {
        return financialStatisticsService.deleteTransaction(id);
    }

    // Retorna transações paginadas com todos os campos do banco
    @GetMapping("/paged")
    public Page<Transaction> getTransactionsPaged(Pageable pageable) {
        return financialStatisticsService.getTransactionsPaged(pageable);
    }

    // Busca uma transação pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {
        return financialStatisticsService.getTransactionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Atualiza uma transação pelo ID
    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable Long id, @RequestBody Transaction updatedTransaction) {
        return financialStatisticsService.updateTransaction(id, updatedTransaction)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
