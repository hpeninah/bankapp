package springboot.api.bankapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.api.bankapp.data.models.Account;
import springboot.api.bankapp.data.models.Transaction;
import springboot.api.bankapp.data.repository.AccountRepository;
import springboot.api.bankapp.data.repository.TransactionRepository;
import springboot.api.bankapp.exceptions.InvalidInputException;
import springboot.api.bankapp.exceptions.TransactionNotFoundException;

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
    public Transaction getTransaction(Long transactionId) throws TransactionNotFoundException {
        return transactionRepository.findById(transactionId).orElseThrow(() -> new TransactionNotFoundException("Transaction ID: " + transactionId + " does not exist."));
    }

    //Get transactions by the account
    public List<Transaction> getTransactionsByAccount(Long accountId) {
        return transactionRepository.getTransactionsByAccount(accountId);
    }

    //Create a transaction
    public Transaction createTransaction(Transaction transaction) throws TransactionNotFoundException, InvalidInputException {
        Account account = accountRepository.findById(transaction.getAccount().getAccountId()).orElseThrow(() -> new TransactionNotFoundException("Account ID: " + transaction.getAccount().getAccountId() + " does not exist."));

        double accountBal = account.getCurrentBal();
        double transactionBal = transaction.getCurrentBal();

        if(transaction.getTransactionType().equals("Deposit")){
            account.setCurrentBal(accountBal + transactionBal);
        } else if ((transaction.getTransactionType().equals("Withdraw") && accountBal > transactionBal) ||
                (transaction.getTransactionType().equals("Transfer") && accountBal > transactionBal)) {
            account.setCurrentBal(accountBal - transactionBal);
        } else {
            throw new InvalidInputException("Invalid transaction type: " + transaction.getTransactionType());
        }

        accountRepository.save(account);
        transaction.setAccount(account);
        return transactionRepository.save(transaction);
    }

    //Delete a transaction
    public boolean deleteTransaction(Long transactionID) throws TransactionNotFoundException{
        transactionRepository.findById(transactionID).orElseThrow(() -> new TransactionNotFoundException("Transaction ID: " + transactionID + " does not exist."));
        transactionRepository.deleteById(transactionID);
        return true;
    }
}