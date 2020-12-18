package dariomorgrane.emphasoft.service.interfaces;

import dariomorgrane.emphasoft.model.ExchangeOperation;
import dariomorgrane.emphasoft.model.User;

import java.util.List;

public interface UserService {

    User save(User user);

    User findByIdOrGetNew(Long id);

    List<User> getAllFilteredBySingleRequest(double limit);

    List<User> getAllFilteredByCommonRequests(double limit);

    ExchangeOperation saveAndGetAddedExchangeOperation(User userBeforeUpdate);
}
