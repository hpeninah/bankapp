package springboot.api.bankapp.data.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @SequenceGenerator(name="ACCOUNT_GENERATOR", sequenceName = "ACCOUNTS")
    @GeneratedValue(strategy = GenerationType.AUTO, generator="ACCOUNT_GENERATOR")
    Long accountId;
    String accountType;
    double currentBal;

    @ManyToOne
    @JoinColumn(name="customerId", nullable = false)
    public Customer customer;

    @OneToMany(mappedBy = "account")
    private List<Transaction> transaction;

    public Account() {
    }

    public Account(Long accountId, double currentBal, String accountType){
        this.accountId = accountId;
        this.currentBal = currentBal;
        this.accountType = accountType;
    }

    public Account(double currentBal) {
        this.currentBal = currentBal;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public double getCurrentBal() { return currentBal; }

    public void setCurrentBal(double currentBal) { this.currentBal = currentBal; }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Transaction> getTransaction(){
        return transaction;
    }

    public void setTransaction(List<Transaction> transaction) {
        this.transaction = transaction;
    }
}