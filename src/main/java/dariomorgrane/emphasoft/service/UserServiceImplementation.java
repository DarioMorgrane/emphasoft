package dariomorgrane.emphasoft.service;

import dariomorgrane.emphasoft.model.ExchangeOperation;
import dariomorgrane.emphasoft.model.User;
import dariomorgrane.emphasoft.repository.UserRepository;
import dariomorgrane.emphasoft.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository repository;

    @Autowired
    public UserServiceImplementation(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public User findByIdOrGetNew(Long id) {
        return repository.findById(id)
                .orElseGet(() -> {
                    User user = new User();
                    user.setExchangeOperations(new HashSet<>());
                    user.setId(id);
                    return user;
                });
    }

    @Override
    public ExchangeOperation saveAndGetAddedExchangeOperation(User userBeforeUpdate) {
        User userUpdated = save(userBeforeUpdate);
        return getAddedExchangeOperation(userBeforeUpdate, userUpdated);
    }

    @Override
    public List<User> getAllFilteredBySingleRequest(double limit) {
        return repository.getAllFilteredBySingleRequest(limit);
    }

    @Override
    public List<User> getAllFilteredByCommonRequests(double limit) {
        return repository.getAllFilteredByCommonRequests(limit);
    }

    private ExchangeOperation getAddedExchangeOperation(User userBeforeUpdate, User userUpdated) {
        if (userUpdated.getExchangeOperations().size() == 1) {
            return new ArrayList<>(userUpdated.getExchangeOperations()).get(0);
        } else {
            Set<ExchangeOperation> exchangeOperationsBeforeUpdate = new HashSet<>(userBeforeUpdate.getExchangeOperations());
            Set<ExchangeOperation> exchangeOperationsAfterUpdate = new HashSet<>(userUpdated.getExchangeOperations());
            exchangeOperationsAfterUpdate.removeAll(exchangeOperationsBeforeUpdate);
            return new ArrayList<>(exchangeOperationsAfterUpdate).get(0);
        }
    }

}
