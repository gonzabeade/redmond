package com.redmond.bankapi.forms

import jakarta.validation.constraints.*

data class PostAccountForm(
        @field:NotEmpty(message = "CBU is required")
        @field:Pattern(regexp = "^[0-9]+\$", message = "CBU must be numeric")
        @field:Size(min = 22, max = 22, message = "CBU must be 22 digits")
        val cbu: String?,

        @field:NotEmpty(message = "CUIL is required")
        @field:Pattern(regexp = "^[0-9]+\$", message = "CUIL must be numeric")
        @field:Size(min = 11, max = 11, message = "CUIL must be 11 digits")
        val cuil: String?
)
