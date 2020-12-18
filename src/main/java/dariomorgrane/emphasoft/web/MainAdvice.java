package dariomorgrane.emphasoft.web;

import dariomorgrane.emphasoft.exception.ExternalApiExchangeException;
import dariomorgrane.emphasoft.exception.WebLayerException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class MainAdvice {

    @ResponseBody
    @ExceptionHandler(WebLayerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String WebLayerExceptionHandler(WebLayerException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ExternalApiExchangeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String ExternalApiExchangeExceptionHandler(ExternalApiExchangeException ex) {
        return ex.getMessage();
    }

}
