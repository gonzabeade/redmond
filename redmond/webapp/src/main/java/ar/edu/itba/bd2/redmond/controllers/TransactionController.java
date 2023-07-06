package ar.edu.itba.bd2.redmond.controllers;

import ar.edu.itba.bd2.redmond.dto.TransactionDto;
import ar.edu.itba.bd2.redmond.form.StartTransactionForm;
import ar.edu.itba.bd2.redmond.model.exceptions.TransactionNotFoundException;
import ar.edu.itba.bd2.redmond.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Transactions")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Operation(summary = "Start a transaction")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transaction started"),
            @ApiResponse(responseCode = "400", description = "Invalid transaction data", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(hidden = true)))
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionDto startTransaction(@Valid @RequestBody StartTransactionForm form, Authentication auth) {
        return new TransactionDto(
                transactionService.startTransaction(
                auth.getName(),
                form.getDestination(),
                form.getAmount(),
                form.getDescription()
            )
        );
    }

    @Operation(summary = "Get a transaction by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "404", description = "Transaction not found", content = @Content(schema = @Schema(hidden = true)))
    })
    @PostAuthorize("returnObject.source == authentication.name || returnObject.destination == authentication.name")
    @GetMapping("/{id}")
    public TransactionDto getTransaction(@PathVariable long id) {
        return new TransactionDto(transactionService.findById(id).orElseThrow(TransactionNotFoundException::new));
    }

    @Operation(summary = "Get transactions for user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping
    public List<TransactionDto> getTransactions(@RequestParam String redmondId) {
//        return transactionService.findAll();
        throw new NotImplementedException();
    }
}
