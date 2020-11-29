package dariomorgrane.emphasoft.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    private long id;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExchangeOperation> exchangeOperations;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<ExchangeOperation> getExchangeOperations() {
        return exchangeOperations;
    }

    public void setExchangeOperations(List<ExchangeOperation> exchangeOperations) {
        this.exchangeOperations = exchangeOperations;
    }
}
