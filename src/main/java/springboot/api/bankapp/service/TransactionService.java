package springboot.api.bankapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.api.bankapp.data.models.Account;
import springboot.api.bankapp.data.models.Transaction;
import springboot.api.bankapp.data.repository.AccountRepository;
import springboot.api.bankapp.data.repository.TransactionRepository;

import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    //Get all transactions
    public List<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    //Get single transaction
    public Transaction getTransaction(Long transactionId) {
        return transactionRepository.findById(transactionId).orElseThrow(() -> new IllegalStateException("Transaction ID: " + transactionId + " does not exist."));
    }

    //Get transactions by the account
    public List<Transaction> getTransactionsByAccount(Long accountId) {
        return transactionRepository.getTransactionsByAccount(accountId);
    }

    //Create a transaction
    public Transaction createTransaction(Transaction transaction) {
        Account account = accountRepository.findById(transaction.getAccount().getAccountId()).orElseThrow(() -> new IllegalStateException("Fail"));
        transaction.setAccount(account);

        return transactionRepository.save(transaction);
    }

    //Delete a transaction
    public boolean deleteTransaction(Long transactionID) {
        transactionRepository.findById(transactionID).orElseThrow(() -> new IllegalStateException("Transaction ID: " + transactionID + " does not exist."));

        try {
            transactionRepository.deleteById(transactionID);
        } catch(Exception exception) {
            System.out.println("Error: " + exception);
            return false;
        }

        return true;
    }
}