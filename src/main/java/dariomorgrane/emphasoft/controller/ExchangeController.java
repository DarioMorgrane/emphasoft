package dariomorgrane.emphasoft.controller;

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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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
        LOGGER.info("Received JSON message mapped to object: " + request);
        User user = userService.mapToModel(request);
        ExchangeOperation newExchangeOperation = exchangeOperationService.mapToModel(request);
        Set<ExchangeOperation> setBeforeSave = new HashSet<>(user.getExchangeOperations());
        user.getExchangeOperations().add(newExchangeOperation);
        newExchangeOperation.setUser(user);
        user = userService.save(user);
        Set<ExchangeOperation> setAfterSave = user.getExchangeOperations();
        setAfterSave.removeAll(setBeforeSave);
        newExchangeOperation = new ArrayList<>(setAfterSave).get(0);
        LOGGER.info("Saved ExchangeOperation object is: " + newExchangeOperation);
        return exchangeOperationService.mapToJson(newExchangeOperation);
    }

}
