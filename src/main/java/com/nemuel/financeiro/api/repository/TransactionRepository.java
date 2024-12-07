package com.nemuel.financeiro.api.repository;

import com.nemuel.financeiro.api.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
