package dariomorgrane.emphasoft.exception.advice;

import dariomorgrane.emphasoft.exception.UnknownValueOfTypeParameterException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UnknownValueOfTypeParameterAdvice {
    @ResponseBody
    @ExceptionHandler(UnknownValueOfTypeParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String animalTypeNotFoundHandler(UnknownValueOfTypeParameterException ex) {
        return ex.getMessage();
    }
}
