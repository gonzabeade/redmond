package com.redmond.bankapi.forms

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern

data class PatchTransactionForm(
        @field:NotNull
        @field:Pattern(regexp = "^(approved|rejected)\$", message = "Status must be 'approved' or 'rejected'")
        val status: String?
)