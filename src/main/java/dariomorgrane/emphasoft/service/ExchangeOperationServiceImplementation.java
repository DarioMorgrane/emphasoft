package dariomorgrane.emphasoft.service;

import dariomorgrane.emphasoft.dto.ExternalApiExchangeResult;
import dariomorgrane.emphasoft.dto.RatingJson;
import dariomorgrane.emphasoft.dto.RequestJson;
import dariomorgrane.emphasoft.dto.ResponseJson;
import dariomorgrane.emphasoft.exception.ExternalApiExchangeException;
import dariomorgrane.emphasoft.model.ExchangeOperation;
import dariomorgrane.emphasoft.repository.ExchangeOperationRepository;
import dariomorgrane.emphasoft.service.interfaces.ExchangeOperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Service
public class ExchangeOperationServiceImplementation implements ExchangeOperationService {

    private final ExchangeOperationRepository repository;
    private final RestTemplate restTemplate = new RestTemplate();
    private static final Logger LOGGER = LoggerFactory.getLogger(ExchangeOperationServiceImplementation.class);

    @Value("${currency-converter-api-url-template}")
    private String currencyConverterApiUrlTemplate;

    @Autowired
    public ExchangeOperationServiceImplementation(ExchangeOperationRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseJson mapToJson(ExchangeOperation exchangeOperation) {
        ResponseJson responseJson = new ResponseJson();
        responseJson.setRequestId(exchangeOperation.getId());
        responseJson.setAmountInTargetCurrency(exchangeOperation.getAmountInTargetCurrency());
        return responseJson;
    }

    @Override
    public List<RatingJson> getRatingOfExchangeDirection() {
        return repository.getRatingOfExchangeDirection();
    }

    @Override
    public ExchangeOperation mapToModel(RequestJson request) {
        ExchangeOperation exchangeOperation = new ExchangeOperation();
        exchangeOperation.setAmountInOriginalCurrency(request.getAmountInOriginalCurrency());
        exchangeOperation.setOriginalCurrency(request.getOriginalCurrency());
        exchangeOperation.setTargetCurrency(request.getTargetCurrency());
        fillUpWithCurrentRates(exchangeOperation);
        return exchangeOperation;
    }

    private void fillUpWithCurrentRates(ExchangeOperation exchangeOperation) {
        double amountInTargetCurrency = defineAmountInTargetCurrency(exchangeOperation.getOriginalCurrency(),
                exchangeOperation.getTargetCurrency(), exchangeOperation.getAmountInOriginalCurrency());
        exchangeOperation.setAmountInTargetCurrency(amountInTargetCurrency);
        double amountInUSD = defineAmountInUSD(exchangeOperation.getOriginalCurrency(), exchangeOperation.getAmountInOriginalCurrency());
        exchangeOperation.setAmountInUSD(amountInUSD);
    }

    private double defineAmountInTargetCurrency(String originalCurrency, String targetCurrency, double amountInOriginalCurrency) {
        String requestUrl = currencyConverterApiUrlTemplate
                .replaceAll("targetCurrency", targetCurrency)
                .replaceAll("originalCurrency", originalCurrency);
        double quotation = 0;
        try {
            ExternalApiExchangeResult externalApiExchangeResult = restTemplate.getForObject(requestUrl, ExternalApiExchangeResult.class);
            quotation = (Double) (Objects.requireNonNull(externalApiExchangeResult).getRates().get(targetCurrency));
        } catch (Exception e) {
            String exceptionExplanation = "Error caused by external exchange api: " + e.getMessage();
            LOGGER.error(exceptionExplanation);
            throw new ExternalApiExchangeException(exceptionExplanation);
        }
        return quotation * amountInOriginalCurrency;
    }

    private double defineAmountInUSD(String originalCurrency, double amountInOriginalCurrency) {
        return defineAmountInTargetCurrency(originalCurrency, "USD", amountInOriginalCurrency);
    }

}
