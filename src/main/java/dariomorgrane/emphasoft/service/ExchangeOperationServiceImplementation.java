package dariomorgrane.emphasoft.service;

import dariomorgrane.emphasoft.dto.RequestJson;
import dariomorgrane.emphasoft.dto.ResponseJson;
import dariomorgrane.emphasoft.model.ExchangeOperation;
import dariomorgrane.emphasoft.service.interfaces.ExchangeOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ExchangeOperationServiceImplementation implements ExchangeOperationService {

    private final JpaRepository<ExchangeOperation, Long> repository;
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${currency-converter-api-url-template}")
    private String currencyConverterApiUrlTemplate;

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
        double amountInTargetCurrency = defineAmountInTargetCurrency(request.getOriginalCurrency(),
                request.getTargetCurrency(), request.getAmountInOriginalCurrency());
        exchangeOperation.setAmountInTargetCurrency(amountInTargetCurrency);
        double amountInUSD = defineAmountInUSD(request.getOriginalCurrency(), request.getAmountInOriginalCurrency());
        exchangeOperation.setAmountInUSD(amountInUSD);
        return exchangeOperation;
    }

    private double defineAmountInTargetCurrency(String originalCurrency, String targetCurrency, double amountInOriginalCurrency) {
        String requestUrl = currencyConverterApiUrlTemplate
                .replaceAll("firstCurrency", originalCurrency)
                .replaceAll("secondCurrency", targetCurrency);
        String respondJson = restTemplate.getForObject(requestUrl, String.class);
        String quotation = respondJson.split(":")[1].replace('}', ' ');
        return Double.parseDouble(quotation) * amountInOriginalCurrency;
    }

    private double defineAmountInUSD(String originalCurrency, double amountInOriginalCurrency) {
        return defineAmountInTargetCurrency(originalCurrency, "USD", amountInOriginalCurrency);
    }

}
