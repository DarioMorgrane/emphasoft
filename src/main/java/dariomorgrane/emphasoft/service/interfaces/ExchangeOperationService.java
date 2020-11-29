package dariomorgrane.emphasoft.service.interfaces;

import dariomorgrane.emphasoft.dto.RequestJson;
import dariomorgrane.emphasoft.model.ExchangeOperation;
import dariomorgrane.emphasoft.model.User;

public interface ExchangeOperationService {

    ExchangeOperation mapToModel(RequestJson request);

}
