package com.nemuel.financeiro.api.controller;

import com.nemuel.financeiro.api.entity.Transaction;
import com.nemuel.financeiro.api.repository.TransactionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin(origins = "http://localhost:3000")
public class TransactionController {

    private final TransactionRepository transactionRepository;

    // Construtor para injeção de dependência
    public TransactionController(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    // Retorna todas as transações do banco de dados
    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    // Adiciona uma nova transação ao banco de dados
    @PostMapping
    public Transaction addTransaction(@RequestBody Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    // Remove uma transação com base no ID fornecido
    @DeleteMapping("/{id}")
    public String deleteTransaction(@PathVariable Long id) {
        if (transactionRepository.existsById(id)) {
            transactionRepository.deleteById(id);
            return "Transaction removed successfully!";
        }
        return "Transaction not found!";
    }
}
