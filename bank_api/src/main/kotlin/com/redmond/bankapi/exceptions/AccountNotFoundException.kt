package com.redmond.bankapi.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND, reason = "Account not found")
class AccountNotFoundException(message: String? = null) : Exception(message)
