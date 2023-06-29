package com.redmond.bankapi.forms

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull

data class PatchAccountForm (
    @field:NotNull
    @field:Min(0)
    val balance: Double?
)