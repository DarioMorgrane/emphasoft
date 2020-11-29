package dariomorgrane.emphasoft.controller;

import dariomorgrane.emphasoft.dto.RequestJson;
import dariomorgrane.emphasoft.dto.ResponseJson;
import dariomorgrane.emphasoft.model.ExchangeOperation;
import dariomorgrane.emphasoft.model.User;
import dariomorgrane.emphasoft.service.interfaces.ExchangeOperationService;
import dariomorgrane.emphasoft.service.interfaces.UserService;
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

    @Autowired
    public ExchangeController(UserService userService, ExchangeOperationService exchangeOperationService) {
        this.userService = userService;
        this.exchangeOperationService = exchangeOperationService;
    }

    @PostMapping
    public ResponseJson handleExchange(@RequestBody RequestJson request) {
        User user = userService.mapToModel(request);
        ExchangeOperation exchangeOperation = exchangeOperationService.mapToModel(request);
        user.getExchangeOperations().add(exchangeOperation);
        user = userService.save(user);
        //return userService.mapToJson(user);
        return new ResponseJson();
    }

}
