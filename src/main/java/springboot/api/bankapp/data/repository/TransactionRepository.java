package springboot.api.bankapp.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import springboot.api.bankapp.data.models.Transaction;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT s FROM Transaction s WHERE s.account.accountId = ?1")
    List<Transaction> getTransactionsByAccount(Long accountId);
}
