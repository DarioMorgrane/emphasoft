package dariomorgrane.emphasoft.service;

import dariomorgrane.emphasoft.dto.RequestJson;
import dariomorgrane.emphasoft.dto.ResponseJson;
import dariomorgrane.emphasoft.model.ExchangeOperation;
import dariomorgrane.emphasoft.repository.ExchangeOperationRepository;
import dariomorgrane.emphasoft.service.interfaces.ExchangeOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public class ExchangeOperationServiceImplementation implements ExchangeOperationService {

    private JpaRepository<ExchangeOperation, Long> repository;

    @Autowired
    public ExchangeOperationServiceImplementation(JpaRepository<ExchangeOperation, Long> repository) {
        this.repository = repository;
    }

    @Override
    public ExchangeOperation save(ExchangeOperation exchangeOperation) {
        return repository.save(exchangeOperation);
    }

    @Override
    public ResponseJson mapToJson(ExchangeOperation exchangeOperation) {
        ResponseJson responseJson = new ResponseJson();
        responseJson.setRequestId(exchangeOperation.getId());
        responseJson.setAmountInTargetCurrency(exchangeOperation.getAmountInTargetCurrency());
        return responseJson;
    }

    @Override
    public ExchangeOperation mapToModel(RequestJson request) {
        ExchangeOperation exchangeOperation = new ExchangeOperation();
        exchangeOperation.setAmountInOriginalCurrency(request.getAmountInOriginalCurrency());
        exchangeOperation.setOriginalCurrency(request.getOriginalCurrency());
        exchangeOperation.setTargetCurrency(request.getTargetCurrency());

        exchangeOperation.setAmountInTargetCurrency(request.getAmountInOriginalCurrency());
        exchangeOperation.setAmountInUSD(request.getAmountInOriginalCurrency()); // fixme and line above - implement exchange
        return  exchangeOperation;
    }

}
