package com.redmond.bankapi.services

import com.redmond.bankapi.dto.TransactionDto
import com.redmond.bankapi.exceptions.AccountNotFoundException
import com.redmond.bankapi.exceptions.BadRequestException
import com.redmond.bankapi.exceptions.InsufficientFundsException
import com.redmond.bankapi.exceptions.TransactionNotFoundException
import com.redmond.bankapi.models.Transaction
import com.redmond.bankapi.models.TransactionStatus
import com.redmond.bankapi.repositories.jpa.JpaAccountRepository
import com.redmond.bankapi.repositories.jpa.JpaTransactionRepository
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class TransactionService {
    @Autowired
    lateinit var accountRepo: JpaAccountRepository

    @Autowired
    lateinit var transactionRepository: JpaTransactionRepository

    fun getTransaction(id: Long): TransactionDto =
            TransactionDto(transactionRepository.findById(id).orElseThrow { TransactionNotFoundException() })

    @Transactional
    fun startTransaction(fromString: String?, toString: String?, desc: String?, amount: Double): TransactionDto {
        if(fromString.isNullOrEmpty() && toString.isNullOrEmpty())
            throw BadRequestException("Either from or to CBU must be sent")

        val from = fromString?.toBigInteger()
        val to = toString?.toBigInteger()

        val maybeFrom = if(from != null) accountRepo.findById(from) else Optional.empty()
        val maybeTo = if(to != null) accountRepo.findById(to) else Optional.empty()

        if(!maybeFrom.isPresent && !maybeTo.isPresent) throw AccountNotFoundException("Accounts not found: $from, $to")

        maybeFrom.ifPresent {
            if(it.balance < amount) {
                throw InsufficientFundsException("Insufficient funds in account: $from")
            }

            it.balance -= amount

            accountRepo.save(it)
        }

        maybeTo.ifPresent {
            it.balance += amount
            accountRepo.save(it)
        }

        val status =
            if((maybeTo.isPresent || toString == null) && (maybeFrom.isPresent || fromString == null))
                TransactionStatus.APPROVED
            else
                TransactionStatus.PENDING

        return TransactionDto(transactionRepository.save(Transaction(from, to, desc, amount, status)))
    }

    @Transactional
    fun updateTransactionStatus(id: Long, status: String): TransactionDto {
        val transaction = transactionRepository.findById(id).orElseThrow { TransactionNotFoundException() }

        transaction.status = TransactionStatus.fromString(status)

        return TransactionDto(transactionRepository.save(transaction))
    }
}