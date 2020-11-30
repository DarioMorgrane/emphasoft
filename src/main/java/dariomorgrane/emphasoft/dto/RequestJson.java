package dariomorgrane.emphasoft.dto;

public class RequestJson {

    private long userId;
    private double amountInOriginalCurrency;
    private String originalCurrency;
    private String targetCurrency;

    public RequestJson() {
    }

    public RequestJson(long userId, double amountInOriginalCurrency, String originalCurrency, String targetCurrency) {
        this.userId = userId;
        this.amountInOriginalCurrency = amountInOriginalCurrency;
        this.originalCurrency = originalCurrency;
        this.targetCurrency = targetCurrency;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public double getAmountInOriginalCurrency() {
        return amountInOriginalCurrency;
    }

    public void setAmountInOriginalCurrency(double amountInOriginalCurrency) {
        this.amountInOriginalCurrency = amountInOriginalCurrency;
    }

    public String getOriginalCurrency() {
        return originalCurrency;
    }

    public void setOriginalCurrency(String originalCurrency) {
        this.originalCurrency = originalCurrency;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(String targetCurrency) {
        this.targetCurrency = targetCurrency;
    }
}
