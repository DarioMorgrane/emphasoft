package dariomorgrane.emphasoft.service;

import dariomorgrane.emphasoft.dto.RequestJson;
import dariomorgrane.emphasoft.model.User;
import dariomorgrane.emphasoft.repository.UserRepository;
import dariomorgrane.emphasoft.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
public class UserServiceImplementation implements UserService {

    private final UserRepository repository;

    @Autowired
    public UserServiceImplementation(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User save(User user) { //todo delete me
        return repository.save(user);
    }

    @Override
    public User mapToModel(RequestJson request) {
        return repository.findById(request.getUserId())
                .orElseGet(() -> {
                    User user = new User();
                    user.setExchangeOperations(new HashSet<>());
                    user.setId(request.getUserId());
                    return user;
                });
    }

    @Override
    public List<User> getAllFilteredBySingleRequest(double limit) {
        return repository.getAllFilteredBySingleRequest(limit);
    }

    @Override
    public List<User> getAllFilteredByCommonRequests(double limit) {
        return repository.getAllFilteredByCommonRequests(limit);
    }


}
