package dariomorgrane.emphasoft.repository;

import dariomorgrane.emphasoft.dto.RatingJson;
import dariomorgrane.emphasoft.model.ExchangeOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExchangeOperationRepository extends JpaRepository<ExchangeOperation, Long> {

    @Query(value = "select new dariomorgrane.emphasoft.dto.RatingJson (count(ex.id), ex.originalCurrency, ex.targetCurrency) " +
            "from ExchangeOperation ex group by ex.targetCurrency, ex.originalCurrency order by count(ex.id) desc")
    List<RatingJson> getRatingOfExchangeDirection();

}
