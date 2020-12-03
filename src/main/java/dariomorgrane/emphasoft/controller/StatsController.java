package dariomorgrane.emphasoft.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dariomorgrane.emphasoft.exception.UnknownValueOfTypeParameterException;
import dariomorgrane.emphasoft.service.interfaces.ExchangeOperationService;
import dariomorgrane.emphasoft.service.interfaces.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats")
public class StatsController {

    private final UserService userService;
    private final ExchangeOperationService exchangeOperationService;
    private final ObjectMapper mapper = new ObjectMapper();
    private static final Logger LOGGER = LoggerFactory.getLogger(StatsController.class);

    @Autowired
    public StatsController(UserService userService, ExchangeOperationService exchangeOperationService) {
        this.userService = userService;
        this.exchangeOperationService = exchangeOperationService;
    }

    @GetMapping
    public ResponseEntity<String> handleStatsRequest(@RequestParam(name = "type") String type,
                                                     @RequestParam(name = "limit", required = false, defaultValue = "0") double limit) throws Exception {
        LOGGER.info("Received request to GET /stats has type - " + type + " and limit - " + limit);
        String responseBody;
        if (type.equals("single")) {
            responseBody = mapper.writeValueAsString(userService.getAllFilteredBySingleRequest(limit));
            LOGGER.info("Response json is - " + responseBody);
            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        } else if (type.equals("common")) {
            responseBody = mapper.writeValueAsString(userService.getAllFilteredByCommonRequests(limit));
            LOGGER.info("Response json is - " + responseBody);
            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        } else if (type.equals("rating")) {
            responseBody = mapper.writeValueAsString(exchangeOperationService.getRatingOfExchangeDirection());
            LOGGER.info("Response json is - " + responseBody);
            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        } else {
            LOGGER.error("Received unsupported value of \"type\" parameter - " + type);
            throw new UnknownValueOfTypeParameterException(type);
        }
    }

}
