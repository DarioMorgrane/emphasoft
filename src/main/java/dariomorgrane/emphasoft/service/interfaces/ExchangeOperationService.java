package dariomorgrane.emphasoft.service.interfaces;

import dariomorgrane.emphasoft.dto.RatingJson;
import dariomorgrane.emphasoft.dto.RequestJson;
import dariomorgrane.emphasoft.dto.ResponseJson;
import dariomorgrane.emphasoft.model.ExchangeOperation;

import java.util.List;

public interface ExchangeOperationService {

    ExchangeOperation mapToModel(RequestJson request);

    ResponseJson mapToJson(ExchangeOperation exchangeOperation);

    List<RatingJson> getRatingOfExchangeDirection();
}
