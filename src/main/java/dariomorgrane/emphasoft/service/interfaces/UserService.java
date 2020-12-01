package dariomorgrane.emphasoft.service.interfaces;

import dariomorgrane.emphasoft.dto.RequestJson;
import dariomorgrane.emphasoft.model.User;

import java.util.List;

public interface UserService {

    User save(User user);

    User mapToModel(RequestJson request);

    List<User> getAllFilteredBySingleRequest(double limit);

    List<User> getAllFilteredByCommonRequests(double limit);
}
