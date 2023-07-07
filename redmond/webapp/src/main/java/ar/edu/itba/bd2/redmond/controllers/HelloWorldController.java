package ar.edu.itba.bd2.redmond.controllers;

import ar.edu.itba.bd2.redmond.model.Transaction;
import ar.edu.itba.bd2.redmond.model.enums.TransactionStatus;
import ar.edu.itba.bd2.redmond.service.MoneyFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class HelloWorldController {

    private final MoneyFlowService moneyFlowService;

    @Autowired
    public HelloWorldController(MoneyFlowService moneyFlowService) {
        this.moneyFlowService = moneyFlowService;
    }


    @RequestMapping("/hello")
    public String helloWorld() {
        Transaction tx1 = new Transaction(
                1234,
                "mili",
                "gonza",
                BigDecimal.ONE,
                "Entradas de Taylor",
                "",
                "",
                TransactionStatus.APPROVED
        );

//        Transaction tx2 = new Transaction(
//                1234,
//                "gonza",
//                "agus",
//                BigDecimal.ONE,
//                "Drogas",
//                "",
//                "",
//                TransactionStatus.APPROVED
//        );

        moneyFlowService.addTransactionToGraph(tx1);
//        moneyFlowService.addTransactionToGraph(tx2);

        return "Hello, World!";
    }
}
