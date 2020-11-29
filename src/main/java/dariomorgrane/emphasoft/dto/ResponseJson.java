package dariomorgrane.emphasoft.dto;

public class ResponseJson {

    private long RequestId;
    private long amountInTargetCurrency;

    public ResponseJson() {
    }

    public long getRequestId() {
        return RequestId;
    }

    public void setRequestId(long requestId) {
        RequestId = requestId;
    }

    public long getAmountInTargetCurrency() {
        return amountInTargetCurrency;
    }

    public void setAmountInTargetCurrency(long amountInTargetCurrency) {
        this.amountInTargetCurrency = amountInTargetCurrency;
    }
}
