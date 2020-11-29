package dariomorgrane.emphasoft.dto;

public class RequestJson {

    public RequestJson() {
    }

    private long userId;
    private long amountInOriginalCurrency;
    private String originalCurrency;
    private String targetCurrency;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getAmountInOriginalCurrency() {
        return amountInOriginalCurrency;
    }

    public void setAmountInOriginalCurrency(long amountInOriginalCurrency) {
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
