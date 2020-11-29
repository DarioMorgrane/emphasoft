package dariomorgrane.emphasoft.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User {" +
                "id= " + id +
                '}';
    }
}
