package dariomorgrane.emphasoft.controller;

import dariomorgrane.emphasoft.model.User;
import dariomorgrane.emphasoft.service.interfaces.ExchangeOperationService;
import dariomorgrane.emphasoft.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stats")
public class StatsController {

    private final UserService userService;
    private final ExchangeOperationService exchangeOperationService;

    @Autowired
    public StatsController(UserService userService, ExchangeOperationService exchangeOperationService) {
        this.userService = userService;
        this.exchangeOperationService = exchangeOperationService;
    }

    @GetMapping("/users")
    public List<User> handleSingleRequestStats(@RequestParam(name = "type") String type, @RequestParam(name = "limit") double limit) {
        return userService.getAllFilteredBySingleRequest(limit);
    }

}
