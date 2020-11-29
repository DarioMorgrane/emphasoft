package dariomorgrane.emphasoft.service.interfaces;

import dariomorgrane.emphasoft.dto.RequestJson;
import dariomorgrane.emphasoft.dto.ResponseJson;
import dariomorgrane.emphasoft.model.ExchangeOperation;

public interface ExchangeOperationService {

    ExchangeOperation mapToModel(RequestJson request);

    ExchangeOperation save(ExchangeOperation exchangeOperation);

    ResponseJson mapToJson(ExchangeOperation exchangeOperation);
}
