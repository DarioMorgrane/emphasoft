package dariomorgrane.emphasoft.exception;

public class ExternalApiExchangeException extends RuntimeException{
    public ExternalApiExchangeException(String msg) {
        super("Some problem with external Api - " + msg);
    }
}
