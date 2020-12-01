package dariomorgrane.emphasoft.service;

import dariomorgrane.emphasoft.dto.OutsideApiExchangeResult;
import dariomorgrane.emphasoft.dto.RatingJson;
import dariomorgrane.emphasoft.dto.RequestJson;
import dariomorgrane.emphasoft.dto.ResponseJson;
import dariomorgrane.emphasoft.model.ExchangeOperation;
import dariomorgrane.emphasoft.repository.ExchangeOperationRepository;
import dariomorgrane.emphasoft.service.interfaces.ExchangeOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ExchangeOperationServiceImplementation implements ExchangeOperationService {

    private final ExchangeOperationRepository repository;
    private final RestTemplate restTemplate = new RestTemplate();

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
    public ExchangeOperation mapToModel(RequestJson request) {  //todo separate method coz hre not only mapping
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

    //todo exception handling
    private double defineAmountInTargetCurrency(String originalCurrency, String targetCurrency, double amountInOriginalCurrency) {
        String requestUrl = currencyConverterApiUrlTemplate
                .replaceAll("targetCurrency", targetCurrency)
                .replaceAll("originalCurrency", originalCurrency);
        OutsideApiExchangeResult outsideApiExchangeResult = restTemplate.getForObject(requestUrl, OutsideApiExchangeResult.class);
        Double quotation = (Double) (outsideApiExchangeResult.getRates().get(targetCurrency));
        return quotation * amountInOriginalCurrency;
    }

    private double defineAmountInUSD(String originalCurrency, double amountInOriginalCurrency) {
        return defineAmountInTargetCurrency(originalCurrency, "USD", amountInOriginalCurrency);
    }

}
