package com.redmond.bankapi.repositories.jpa

import com.redmond.bankapi.models.Transaction
import com.redmond.bankapi.repositories.TransactionRepository
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface JpaTransactionRepository : TransactionRepository, JpaRepository<Transaction, Long>
