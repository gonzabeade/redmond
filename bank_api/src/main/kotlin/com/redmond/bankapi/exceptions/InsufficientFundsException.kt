package com.redmond.bankapi.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.FORBIDDEN, reason = "Insufficient funds")
class InsufficientFundsException(message: String? = null) : Exception(message)
