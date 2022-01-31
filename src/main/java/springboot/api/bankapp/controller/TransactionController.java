package springboot.api.bankapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springboot.api.bankapp.data.models.Transaction;
import springboot.api.bankapp.service.TransactionService;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@CrossOrigin
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("")
    public List<Transaction> getTransactions() {
        return transactionService.getTransactions();
    }

    @GetMapping("/{transactionId}")
    public Transaction getTransactionById(@PathVariable("transactionId") Long transactionId) {
        try{
            return transactionService.getTransaction(transactionId);
        } catch(IllegalStateException exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }

    @GetMapping("/account/{accountId}")
    public List<Transaction> getTransactionsByAccount(@PathVariable("accountId") Long accountId) {
        return transactionService.getTransactionsByAccount(accountId);
    }

    @PostMapping("")
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionService.createTransaction(transaction);
    }

    @DeleteMapping("/{transactionId}")
    public boolean deleteTransaction(@PathVariable("transactionId") Long transactionId) {
        try {
            transactionService.deleteTransaction(transactionId);
            return true;
        } catch(IllegalStateException exception) {
            System.out.println(exception.getMessage());
            return false;
        }
    }
}
