package com.redmond.bankapi.models

import com.redmond.bankapi.exceptions.InvalidStatusException

enum class TransactionStatus {
    PENDING,
    APPROVED,
    REJECTED;

    companion object {
        fun fromString(value: String): TransactionStatus {
            return when(value.lowercase()) {
                "pending" -> PENDING
                "approved" -> APPROVED
                "rejected" -> REJECTED
                else -> throw InvalidStatusException("Invalid status: '$value'")
            }
        }
    }
}