package springboot.api.bankapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;
import springboot.api.bankapp.data.models.Account;
import springboot.api.bankapp.service.AccountService;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@CrossOrigin
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
    public Account getAccountById(@PathVariable("accountId") Long accountId) {
        try{
            return accountService.getAccount(accountId);
        } catch(IllegalStateException exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }

    @GetMapping("/customer/{customerId}")
    public List<Account> getAccountsByCustomer(@PathVariable Long customerId) {
        return accountService.getAccountByCustomer(customerId);
    }

    @PostMapping("")
    public Account createAccount(@RequestBody Account account) { return accountService.createAccount(account); }

    @PutMapping("/{accountId}")
    public Account updateAccount(@PathVariable("accountId") Long accountId, Account account){
        try{
            Account updatedAccount = accountService.updateAccount(accountId, account);
            return updatedAccount;
        } catch(IllegalStateException exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }

    @DeleteMapping("/{accountId}")
    public boolean deleteAccount(@PathVariable("accountId") Long accountId ) {
        try {
            accountService.deleteAccount(accountId);
            return true;
        } catch (IllegalStateException exception) {
            System.out.println(exception.getMessage());
            return false;
        }
    }
}
