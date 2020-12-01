package dariomorgrane.emphasoft.exception.advice;

import dariomorgrane.emphasoft.exception.ExternalApiExchangeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExternalApiExchangeAdvice {
    @ResponseBody
    @ExceptionHandler(ExternalApiExchangeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String ExternalApiExchangeExceptionHandler(ExternalApiExchangeException ex) {
        return ex.getMessage();
    }
}
