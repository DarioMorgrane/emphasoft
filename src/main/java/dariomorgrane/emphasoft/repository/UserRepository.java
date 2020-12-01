package dariomorgrane.emphasoft.repository;

import dariomorgrane.emphasoft.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT DISTINCT(u) FROM User AS u JOIN u.exchangeOperations AS e WHERE e.amountInUSD > :limit")
    List<User> getAllFilteredBySingleRequest(@Param("limit") double limit);

    @Query("SELECT u FROM User AS u JOIN u.exchangeOperations AS e GROUP BY u HAVING SUM(e.amountInUSD) > :limit")
    List<User> getAllFilteredByCommonRequests(@Param("limit") double limit);
}
