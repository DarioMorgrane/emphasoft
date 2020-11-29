package dariomorgrane.emphasoft.controller;

import dariomorgrane.emphasoft.dto.RequestJson;
import dariomorgrane.emphasoft.dto.ResponseJson;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exchange")
public class ExchangeController {

    @PostMapping
    public ResponseJson handleExchange(@RequestBody RequestJson request) {
        return new ResponseJson();
    }

}
