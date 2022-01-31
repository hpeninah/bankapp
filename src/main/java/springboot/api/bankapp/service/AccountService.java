package springboot.api.bankapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.api.bankapp.data.models.Account;
import springboot.api.bankapp.data.models.Customer;
import springboot.api.bankapp.data.repository.AccountRepository;
import springboot.api.bankapp.data.repository.CustomerRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public AccountService(CustomerRepository customerRepository, AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    //Get all accounts
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    //Get single account
    public Account getAccount(Long accountId) {
        return  accountRepository.findById(accountId).orElseThrow(() -> new IllegalStateException("Account ID: " + accountId + " does not exist."));
    }

    //Get an account by the customer
    public List<Account> getAccountByCustomer(Long customerId) {
        return accountRepository.selectAccountsByCustomer(customerId);
    }

    //Create an account
    public Account createAccount(Account account) {
        Customer customer = customerRepository.findById(account.getCustomer().getCustomerId()).orElseThrow(() -> new IllegalStateException("Not allowed."));
        account.setCustomer(customer);
        return accountRepository.save(account);
    }

    //Update an account
    @Transactional
    public Account updateAccount(Long accountId, Account account) {
        Account updatedAccount = accountRepository.findById(accountId).orElseThrow(() ->  new IllegalStateException("Customer with ID: " + accountId + "does not exist."));

        if(account.getCurrentBal() > 0) {
            updatedAccount.setCurrentBal(account.getCurrentBal());
        }
        return accountRepository.save(updatedAccount);
    }

    //Delete an account
    public boolean deleteAccount(Long accountId) {
        accountRepository.findById(accountId).orElseThrow(() -> new IllegalStateException("Account ID: " + accountId + " does not exist."));

        try{
            accountRepository.deleteById(accountId);
        } catch(Exception exception) {
            System.out.println("Error: " + exception);
            return false;
        }
        return true;
    }
}
