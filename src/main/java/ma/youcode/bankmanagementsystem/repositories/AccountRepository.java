package ma.youcode.bankmanagementsystem.repositories;

import ma.youcode.bankmanagementsystem.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAllByUserId(Long userId);
}
