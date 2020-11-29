package dariomorgrane.emphasoft.service.interfaces;

import dariomorgrane.emphasoft.dto.RequestJson;
import dariomorgrane.emphasoft.model.User;

public interface UserService {

    User save(User user);

    User mapToModel(RequestJson request);

}
