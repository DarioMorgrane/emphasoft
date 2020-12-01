package dariomorgrane.emphasoft.exception;

public class UnknownValueOfTypeParameterException extends RuntimeException {
    public UnknownValueOfTypeParameterException(String type) {
        super("Unknown value of type parameter - " + type);
    }
}