package com.redmond.bankapi.dto

import com.redmond.bankapi.models.Account
import java.math.BigInteger

data class AccountDto (
        val cbu: String,
        val cuil: String,
        val balance: Double
) {
    constructor(account: Account) : this(account.cbu.toString(), account.cuil.toString(), account.balance)
}
