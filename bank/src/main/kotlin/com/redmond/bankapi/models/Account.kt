package com.redmond.bankapi.models

import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.math.BigInteger
import jakarta.persistence.Id as JpaId


@Entity
@Table(name = "account")
data class Account(
        @JpaId
        val cbu: BigInteger,

        val cuil: Long,
        var balance: Double = 0.0,
)
