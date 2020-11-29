package dariomorgrane.emphasoft.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "exchange_operations")
public class ExchangeOperation {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExchangeOperation)) return false;
        ExchangeOperation that = (ExchangeOperation) o;
        return id == that.id &&
                amountInOriginalCurrency == that.amountInOriginalCurrency &&
                amountInTargetCurrency == that.amountInTargetCurrency &&
                amountInUSD == that.amountInUSD &&
                Objects.equals(originalCurrency, that.originalCurrency) &&
                Objects.equals(targetCurrency, that.targetCurrency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, originalCurrency, targetCurrency, amountInOriginalCurrency, amountInTargetCurrency, amountInUSD);
    }

    @Override
    public String toString() {
        return "ExchangeOperation {" +
                "id= " + id +
                ", originalCurrency= '" + originalCurrency + '\'' +
                ", targetCurrency= '" + targetCurrency + '\'' +
                ", amountInOriginalCurrency= " + amountInOriginalCurrency +
                ", amountInTargetCurrency= " + amountInTargetCurrency +
                ", amountInUSD= " + amountInUSD +
                '}';
    }
}
