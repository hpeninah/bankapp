package springboot.api.bankapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.api.bankapp.data.models.Account;
import springboot.api.bankapp.data.models.Customer;
import springboot.api.bankapp.data.repository.AccountRepository;
import springboot.api.bankapp.data.repository.CustomerRepository;
import springboot.api.bankapp.exceptions.AccountNotFoundException;

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
    public Account getAccount(Long accountId) throws AccountNotFoundException {
        return  accountRepository.findById(accountId).orElseThrow(() -> new AccountNotFoundException("Account ID: " + accountId + " does not exist."));
    }

    //Get an account by the customer
    public List<Account> getAccountByCustomer(Long customerId) {
        return accountRepository.selectAccountsByCustomer(customerId);
    }

    //Create an account
    public Account createAccount(Account account) throws AccountNotFoundException{
        Customer customer = customerRepository.findById(account.getCustomer().getCustomerId()).orElseThrow(() -> new AccountNotFoundException("Not allowed."));
        account.setCustomer(customer);
        return accountRepository.save(account);
    }

    //Update an account
    @Transactional
    public Account updateAccount(Long accountId, Account account) throws AccountNotFoundException{
        Account updatedAccount = accountRepository.findById(accountId).orElseThrow(() ->  new AccountNotFoundException("Customer with ID: " + accountId + "does not exist."));
        return accountRepository.save(updatedAccount);
    }

    //Delete an account
    public boolean deleteAccount(Long accountId) throws AccountNotFoundException{
        accountRepository.findById(accountId).orElseThrow(() -> new AccountNotFoundException("Account ID: " + accountId + " does not exist."));
        accountRepository.deleteById(accountId);
        return true;
    }
}
