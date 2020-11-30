package dariomorgrane.emphasoft.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "requestId", "amountInTargetCurrency"})
public class ResponseJson {

    private long requestId;
    private double amountInTargetCurrency;

    public ResponseJson() {
    }

    public ResponseJson(long requestId, double amountInTargetCurrency) {
        this.requestId = requestId;
        this.amountInTargetCurrency = amountInTargetCurrency;
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public double getAmountInTargetCurrency() {
        return amountInTargetCurrency;
    }

    public void setAmountInTargetCurrency(double amountInTargetCurrency) {
        this.amountInTargetCurrency = amountInTargetCurrency;
    }
}
