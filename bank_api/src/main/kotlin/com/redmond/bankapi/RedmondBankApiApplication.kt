package com.redmond.bankapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@EnableJpaRepositories("com.redmond.bankapi.repositories.jpa")
@EnableTransactionManagement
@SpringBootApplication
class RedmondBankApiApplication

fun main(args: Array<String>) {
    runApplication<RedmondBankApiApplication>(*args)
}
