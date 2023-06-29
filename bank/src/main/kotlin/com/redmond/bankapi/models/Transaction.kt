package com.redmond.bankapi.models

import jakarta.persistence.*
import java.math.BigInteger
import org.springframework.data.annotation.Id as MongoId
import jakarta.persistence.Id as JpaId

@Entity
@Table(name = "transaction")
data class Transaction(
        val fromCbu: BigInteger?,
        val toCbu: BigInteger?,
        val description: String?,
        val amount: Double,

        @Enumerated(EnumType.STRING)
        var status: TransactionStatus = TransactionStatus.PENDING,

        @JpaId
        @GeneratedValue
        val id: Int? = null
)
