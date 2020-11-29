package dariomorgrane.emphasoft.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    private long id;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExchangeRequest> exchangeRequests;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<ExchangeRequest> getExchangeRequests() {
        return exchangeRequests;
    }

    public void setExchangeRequests(List<ExchangeRequest> exchangeRequests) {
        this.exchangeRequests = exchangeRequests;
    }
}
