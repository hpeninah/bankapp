package springboot.api.bankapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springboot.api.bankapp.data.models.Account;
import springboot.api.bankapp.exceptions.AccountNotFoundException;
import springboot.api.bankapp.service.AccountService;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@CrossOrigin("*")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("")
    public List<Account> getAccounts() {
        return accountService.getAccounts();
    }

    @GetMapping("/{accountId}")
    public Account getAccountById(@PathVariable("accountId") Long accountId) throws AccountNotFoundException {
        return accountService.getAccount(accountId);
    }

    @GetMapping("/customer/{customerId}")
    public List<Account> getAccountsByCustomer(@PathVariable Long customerId) {
        return accountService.getAccountByCustomer(customerId);
    }

    @PostMapping("")
    public Account createAccount(@RequestBody Account account) throws AccountNotFoundException { return accountService.createAccount(account); }

    @PutMapping("/{accountId}")
    public Account updateAccount(@PathVariable("accountId") Long accountId, Account account) throws AccountNotFoundException{
        return accountService.updateAccount(accountId, account);
    }

    @DeleteMapping("/{accountId}")
    public boolean deleteAccount(@PathVariable("accountId") Long accountId ) throws AccountNotFoundException {
        return accountService.deleteAccount(accountId);
    }
}
