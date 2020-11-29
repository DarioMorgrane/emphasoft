package dariomorgrane.emphasoft.repository;

import dariomorgrane.emphasoft.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
