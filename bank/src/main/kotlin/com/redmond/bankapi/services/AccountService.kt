package com.redmond.bankapi.services

import com.redmond.bankapi.dto.AccountDto
import com.redmond.bankapi.exceptions.AccountNotFoundException
import com.redmond.bankapi.exceptions.CbuAlreadyExistsException
import com.redmond.bankapi.models.Account
import com.redmond.bankapi.repositories.jpa.JpaAccountRepository
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.math.BigInteger

@Service
class AccountService {
    @Autowired
    lateinit var accountRepo: JpaAccountRepository

    fun getAllPaged(page: Int, size: Int) =
            accountRepo.findAll(PageRequest.of(page - 1, size)).map { AccountDto(it) }

    @Transactional
    fun create(cbuString: String, cuilString: String): AccountDto {
        val cbu = cbuString.toBigInteger()
        val cuil = cuilString.toLong()

        accountRepo.findById(cbu).ifPresent {
            throw CbuAlreadyExistsException()
        }

        return AccountDto(accountRepo.save(Account(cbu, cuil)))
    }

    fun getByCbu(cbu: BigInteger): AccountDto =
            AccountDto(accountRepo.findById(cbu).orElseThrow { AccountNotFoundException() })
}
