package dariomorgrane.emphasoft.service;

import dariomorgrane.emphasoft.dto.RequestJson;
import dariomorgrane.emphasoft.model.ExchangeOperation;
import dariomorgrane.emphasoft.service.interfaces.ExchangeOperationService;
import org.springframework.stereotype.Component;

@Component
public class ExchangeOperationServiceImplementation implements ExchangeOperationService {

    public ExchangeOperation mapToModel(RequestJson request) {
        ExchangeOperation exchangeOperation = new ExchangeOperation();
        exchangeOperation.setAmountInOriginalCurrency(request.getAmountInOriginalCurrency());
        exchangeOperation.setOriginalCurrency(request.getOriginalCurrency());
        exchangeOperation.setTargetCurrency(request.getTargetCurrency());

        //exchangeOperation.setTargetCurrency();
        //exchangeOperation.setAmountInTargetCurrency();
        return  exchangeOperation;
    }

}
