package springboot.api.bankapp.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import springboot.api.bankapp.data.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT s FROM User s WHERE s.username = ?1 AND s.password = ?2")
    User loginUser(String username, String password);
}
