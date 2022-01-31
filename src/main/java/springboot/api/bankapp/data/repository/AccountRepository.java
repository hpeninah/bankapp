package springboot.api.bankapp.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import springboot.api.bankapp.data.models.Account;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("SELECT s FROM Account s WHERE s.customer.customerId = ?1")
    List<Account> selectAccountsByCustomer(Long customerId);
}