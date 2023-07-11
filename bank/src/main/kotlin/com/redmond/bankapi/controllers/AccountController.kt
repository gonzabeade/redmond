package com.redmond.bankapi.controllers

import com.redmond.bankapi.dto.AccountDto
import com.redmond.bankapi.forms.PatchAccountForm
import com.redmond.bankapi.forms.PostAccountForm
import com.redmond.bankapi.services.AccountService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.math.BigInteger

@Tag(name = "Accounts")
@RestController
@RequestMapping("/accounts")
class AccountController {
    @Autowired lateinit var accountService: AccountService

    @Operation(summary = "Get all accounts")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Accounts found"),
        ApiResponse(responseCode = "204", description = "No accounts found", content = [Content(schema = Schema(hidden = true))])
    ])
    @GetMapping
    fun getAccounts(
            @RequestParam(defaultValue = "1") page: Int,
            @RequestParam(defaultValue = "10") size: Int
    ): ResponseEntity<List<AccountDto>> {
        val accounts = accountService.getAllPaged(page, size)
        if(accounts.isEmpty) {
            return ResponseEntity(HttpStatus.NO_CONTENT)
        }

        return ResponseEntity.ok(accounts.content)
    }

    @Operation(summary = "Get account")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Account found"),
        ApiResponse(responseCode = "404", description = "Account not found", content = [Content(schema = Schema(hidden = true))])
    ])
    @GetMapping("/{cbu}")
    fun getAccount(@PathVariable cbu: BigInteger) = accountService.getByCbu(cbu)

    @Operation(summary = "Create account")
    @ApiResponses(value = [
        ApiResponse(responseCode = "201", description = "Account created"),
        ApiResponse(responseCode = "400", description = "Invalid request", content = [Content(schema = Schema(hidden = true))])
    ])
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun postAccount(@Valid @RequestBody form: PostAccountForm) =
            accountService.create(form.cbu!!, form.cuil!!)
}
