package dariomorgrane.emphasoft.model;

import javax.persistence.*;

@Entity
@Table(name = "exchange_requests")
public class ExchangeRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, name = "original_currency", length = 3)
    private String originalCurrency;

    @Column(nullable = false, name = "target_currency", length = 3)
    private String targetCurrency;

    @Column(nullable = false, name = "amount_in_original_currency")
    private long amountInOriginalCurrency;

    @Column(nullable = false, name = "amount_in_target_currency")
    private long amountInTargetCurrency;

    @Column(nullable = false, name = "amount_in_usd")
    private long amountInUSD;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public long getAmountInOriginalCurrency() {
        return amountInOriginalCurrency;
    }

    public void setAmountInOriginalCurrency(long amountInOriginalCurrency) {
        this.amountInOriginalCurrency = amountInOriginalCurrency;
    }

    public long getAmountInTargetCurrency() {
        return amountInTargetCurrency;
    }

    public void setAmountInTargetCurrency(long amountInTargetCurrency) {
        this.amountInTargetCurrency = amountInTargetCurrency;
    }

    public long getAmountInUSD() {
        return amountInUSD;
    }

    public void setAmountInUSD(long amountInUSD) {
        this.amountInUSD = amountInUSD;
    }
}
