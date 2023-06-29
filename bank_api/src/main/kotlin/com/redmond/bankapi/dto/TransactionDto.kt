package com.redmond.bankapi.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.redmond.bankapi.models.Transaction

@JsonInclude(JsonInclude.Include.NON_NULL)
data class TransactionDto(
        val id: String?,
        val from: String?,
        val to: String?,
        val desc: String?,
        val amount: Double,
        val status: String
) {
    constructor(transaction: Transaction) : this(
            transaction.id?.toString(),
            transaction.fromCbu?.toString(),
            transaction.toCbu?.toString(),
            transaction.description,
            transaction.amount,
            transaction.status.name.lowercase()
    )
}
