package ma.youcode.bankmanagementsystem.repositories;

import ma.youcode.bankmanagementsystem.entities.User;
import ma.youcode.bankmanagementsystem.enums.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    Page<User> findAllByRole(Role role, Pageable pageable);

    @Query("SELECT u FROM User u WHERE u.email LIKE %:query% OR u.name LIKE %:query%")
    Page<User> searchByEmailOrName(@Param("query") String query, Pageable pageable);
}
