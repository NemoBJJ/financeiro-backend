package com.nemuel.financeiro.api.repository;

import com.nemuel.financeiro.api.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    // Método para buscar transações paginadas
    Page<Transaction> findAll(Pageable pageable);
}
