package com.redmond.bankapi.services

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

@Service
class TransactionService {
    @Autowired
    lateinit var accountRepo: JpaAccountRepository

    @Autowired
    lateinit var transactionRepository: JpaTransactionRepository

    fun getTransaction(id: Long): Transaction =
            transactionRepository.findById(id).orElseThrow { TransactionNotFoundException() }

    @Transactional
    fun startTransaction(fromString: String?, toString: String?, desc: String?, amount: Double): Transaction {
        if(fromString.isNullOrEmpty() && toString.isNullOrEmpty())
            throw BadRequestException("Either from or to CBU must be sent")

        val from = fromString?.toBigInteger()
        val to = toString?.toBigInteger()

        val maybeFrom = from?.let { accountRepo.findById(from).orElse(null) }
        val maybeTo = to?.let { accountRepo.findById(to).orElse(null) }

        if(maybeFrom == null && maybeTo == null) throw AccountNotFoundException("Accounts not found: $from, $to")

        maybeFrom?.apply {
            if(this.balance < amount) {
                throw InsufficientFundsException("Insufficient funds in account: $from")
            }

            this.balance -= amount

            accountRepo.save(this)
        }

        val status = TransactionStatus.PENDING

        return transactionRepository.save(Transaction(from, to, desc, amount, status))
    }

    @Transactional
    fun updateTransactionStatus(id: Long, status: String): Transaction {
        val newStatus = TransactionStatus.fromString(status)
        val transaction = transactionRepository.findById(id).orElseThrow { TransactionNotFoundException() }

        val from = transaction.fromCbu?.let { accountRepo.findById(it).orElseThrow{IllegalStateException()} }
        val to = transaction.toCbu?.let { accountRepo.findById(it).orElseThrow{IllegalStateException()} }

        if(transaction.status == TransactionStatus.PENDING) {
            when(newStatus) {
                TransactionStatus.APPROVED -> {
                    to?.apply {
                        this.balance += transaction.amount
                        accountRepo.save(this)
                    }
                }

                TransactionStatus.REJECTED -> {
                    from?.apply {
                        this.balance += transaction.amount
                        accountRepo.save(this)
                    }
                }

                else -> {}
            }
        }

        transaction.status = newStatus

        return transactionRepository.save(transaction)
    }
}