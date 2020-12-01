package dariomorgrane.emphasoft.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"numberOfExchanges", "originalCurrency", "targetCurrency"})
public class RatingJson {

    private long numberOfExchanges;
    private String targetCurrency;
    private String originalCurrency;

    public RatingJson() {
    }

    public RatingJson(long numberOfExchanges, String originalCurrency, String targetCurrency) {
        this.numberOfExchanges = numberOfExchanges;
        this.targetCurrency = targetCurrency;
        this.originalCurrency = originalCurrency;
    }

    public long getNumberOfExchanges() {
        return numberOfExchanges;
    }

    public void setNumberOfExchanges(long numberOfExchanges) {
        this.numberOfExchanges = numberOfExchanges;
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
