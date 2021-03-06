package springboot.api.bankapp.data.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long transactionId;
    Long transactionRef;
    String transactionDate;
    String transactionType;
    String transactionSubType;
    Double currentBal;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="accountNumber", nullable = false)
    private Account account;

    public Transaction(){}

    public Transaction(Long transactionId, Long transactionRef, String transactionDate, String transactionType, String transactionSubType, Double currentBal, Account account) {
        this.transactionId = transactionId;
        this.transactionRef = transactionRef;
        this.transactionDate = transactionDate;
        this.transactionType = transactionType;
        this.transactionSubType = transactionSubType;
        this.currentBal = currentBal;
        this.account = account;
    }

    public Transaction(Long transactionRef, String transactionDate, String transactionType, String transactionSubType, Double currentBal, Account account){
        this.transactionRef = transactionRef;
        this.transactionDate = transactionDate;
        this.transactionType = transactionType;
        this.transactionSubType = transactionSubType;
        this.currentBal = currentBal;
        this.account = account;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getTransactionRef() {
        return transactionRef;
    }

    public void setTransactionRef(Long transactionRef) {
        this.transactionRef = transactionRef;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionSubType() {
        return transactionSubType;
    }

    public void setTransactionSubType(String transactionSubType) {
        this.transactionSubType = transactionSubType;
    }

    public Double getCurrentBal() {
        return currentBal;
    }

    public void setCurrentBal(Double currentBal) {
        this.currentBal = currentBal;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String toCSV(){
        return this.transactionDate + ", " +
                this.transactionId + ", " +
                this.transactionRef + ", " +
                this.transactionType + ", " +
                this.transactionSubType + ", " +
                this.currentBal + ", ";
    }
}
