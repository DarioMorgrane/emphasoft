package dariomorgrane.emphasoft.service;

import dariomorgrane.emphasoft.dto.RequestJson;
import dariomorgrane.emphasoft.model.User;
import dariomorgrane.emphasoft.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class UserServiceImplementation implements UserService {

    private final JpaRepository<User, Long> repository;

    @Autowired
    public UserServiceImplementation(JpaRepository<User, Long> repository) {
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
                    user.setExchangeOperations(new ArrayList<>());
                    user.setId(request.getUserId());
                    return user;
                });
    }



}
