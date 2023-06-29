package com.redmond.bankapi.forms

import jakarta.validation.constraints.*

data class PostTransactionForm(
        @field:Pattern(regexp = "^[0-9]+\$", message = "From CBU must be numeric")
        @field:Size(min = 22, max = 22, message = "From CBU must be 22 digits")
        val from: String?,

        @field:Pattern(regexp = "^[0-9]+\$", message = "To CBU must be numeric")
        @field:Size(min = 22, max = 22, message = "To CBU must be 22 digits")
        val to: String?,

        @field:NotNull(message = "Amount is required")
        @field:Min(value = 1, message = "Minimum transaction amount is 1")
        val amount: Double?,

        val desc: String?
)
