package com.redmond.bankapi.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST, reason = "Invalid status")
class InvalidStatusException(message: String? = null) : Exception(message)
