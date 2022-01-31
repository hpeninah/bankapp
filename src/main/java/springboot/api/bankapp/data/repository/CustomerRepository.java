package springboot.api.bankapp.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.api.bankapp.data.models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
