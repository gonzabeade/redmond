package com.redmond.bankapi.repositories.jpa

import com.redmond.bankapi.models.Account
import com.redmond.bankapi.repositories.AccountRepository
import org.springframework.data.jpa.repository.JpaRepository
import java.math.BigInteger

interface JpaAccountRepository : AccountRepository, JpaRepository<Account, BigInteger>