package dariomorgrane.emphasoft.dto;

import java.util.Map;

public class ExchangeResult{
    public ExchangeResult() {
    }

    private String base;
    private String date;
    private Map<String, Object> rates;

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Map<String, Object> getRates() {
        return rates;
    }

    public void setRates(Map<String, Object> rates) {
        this.rates = rates;
    }
}

