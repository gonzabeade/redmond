package ar.edu.itba.bd2.redmond.service;

import ar.edu.itba.bd2.redmond.model.Transaction;
import ar.edu.itba.bd2.redmond.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MoneyFlowServiceImpl implements MoneyFlowService {

    private final UserService userService;

    @Autowired
    public MoneyFlowServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void addTransactionToGraph(Transaction transaction) {
        User src = userService.getUserByRedmondId(transaction.getSource()).orElseThrow( () -> new RuntimeException());
        User dst = userService.getUserByRedmondId(transaction.getDestination()).orElseThrow( () -> new RuntimeException());
        // Llamar al dao

    }

}
