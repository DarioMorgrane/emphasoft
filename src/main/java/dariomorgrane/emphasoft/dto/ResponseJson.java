package dariomorgrane.emphasoft.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "requestId", "amountInTargetCurrency"})
public class ResponseJson {

    private long requestId;
    private long amountInTargetCurrency;

    public ResponseJson() {
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public long getAmountInTargetCurrency() {
        return amountInTargetCurrency;
    }

    public void setAmountInTargetCurrency(long amountInTargetCurrency) {
        this.amountInTargetCurrency = amountInTargetCurrency;
    }
}
