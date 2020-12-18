package dariomorgrane.emphasoft.web;

import dariomorgrane.emphasoft.dto.RequestJson;
import dariomorgrane.emphasoft.dto.ResponseJson;
import dariomorgrane.emphasoft.model.ExchangeOperation;
import dariomorgrane.emphasoft.model.User;
import dariomorgrane.emphasoft.service.interfaces.ExchangeOperationService;
import dariomorgrane.emphasoft.service.interfaces.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exchange")
public class ExchangeController {

    private final UserService userService;
    private final ExchangeOperationService exchangeOperationService;
    private static final Logger LOGGER = LoggerFactory.getLogger(ExchangeController.class);

    @Autowired
    public ExchangeController(UserService userService, ExchangeOperationService exchangeOperationService) {
        this.userService = userService;
        this.exchangeOperationService = exchangeOperationService;
    }

    @PostMapping
    public ResponseJson handleExchange(@RequestBody RequestJson request) {
        LOGGER.info("JSON message received to POST /exchange  mapped to object: " + request);

        User user = userService.findByIdOrGetNew(request.getUserId());
        ExchangeOperation newExchangeOperation = exchangeOperationService.mapToModel(request);
        user.addExchangeOperation(newExchangeOperation);
        ExchangeOperation addedExchangeOperation = userService.saveAndGetAddedExchangeOperation(user);

        LOGGER.info("Saved ExchangeOperation object is: " + addedExchangeOperation);
        return exchangeOperationService.mapToJson(addedExchangeOperation);
    }

}
