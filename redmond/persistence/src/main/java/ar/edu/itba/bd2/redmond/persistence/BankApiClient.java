package ar.edu.itba.bd2.redmond.persistence;


import ar.edu.itba.bd2.redmond.model.BankAccount;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BankApiClient implements BankApiDao {
    private final WebClient client;

    public BankApiClient(String baseUrl) {
        client = WebClient.create(baseUrl);
    }

    public String transfer(String fromAccount, String toAccount, BigDecimal amount, String description) {
        Map<String, Object> body = new HashMap<>();
        body.put("from", fromAccount);
        body.put("to", toAccount);
        body.put("amount", amount);
        body.put("description", description);

        Map<String,Object> responseBody = client.post()
                .uri("/transactions")
                .body(BodyInserters.fromValue(body))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String,Object>>() {})
                .block();

        if(responseBody == null || !responseBody.containsKey("id")) {
            throw new IllegalStateException();
        }

        return responseBody.get("id").toString();
    }

    public String debit(String account, BigDecimal amount, String description) {
        Map<String, Object> body = new HashMap<>();
        body.put("from", account);
        body.put("description", description);
        body.put("amount", amount);

        Map<String,Object> responseBody = client.post()
                .uri("/transactions")
                .body(BodyInserters.fromValue(body))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String,Object>>() {})
                .block();

        if(responseBody == null || !responseBody.containsKey("id")) {
            throw new IllegalStateException();
        }

        return responseBody.get("id").toString();
    }

    public String credit(String account, BigDecimal amount, String description) {
        Map<String, Object> body = new HashMap<>();
        body.put("to", account);
        body.put("description", description);
        body.put("amount", amount);

        Map<String,Object> bodyResponse = client.post()
                .uri("/transactions")
                .body(BodyInserters.fromValue(body))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String,Object>>() {})
                .block();

        if(bodyResponse == null || !bodyResponse.containsKey("id")) {
            throw new IllegalStateException();
        }

        return bodyResponse.get("id").toString();
    }

    @Override
    public Optional<BankAccount> findAccount(String cbu) {
        return client.get()
                .uri("/accounts/{cbu}", cbu)
                .retrieve()
                .onStatus(HttpStatus.NOT_FOUND::equals, response -> Mono.empty())
                .bodyToMono(BankAccount.class)
                .blockOptional();
    }

    @Override
    public void rollbackTransaction(String transactionId) {
        Map<String, String> body = new HashMap<>();
        body.put("status", "rejected");

        client.patch()
                .uri("/transactions/{id}", transactionId)
                .body(BodyInserters.fromValue(body))
                .retrieve()
                .toBodilessEntity()
                .block();
    }

    @Override
    public void commitTransaction(String transactionId) {
        Map<String, String> body = new HashMap<>();
        body.put("status", "approved");

        client.patch()
                .uri("/transactions/{id}", transactionId)
                .body(BodyInserters.fromValue(body))
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}
