package com.project.Repository;

import com.project.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    public Transaction getByCardId(int cardId);
}
