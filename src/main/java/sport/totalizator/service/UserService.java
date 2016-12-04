package sport.totalizator.service;

import sport.totalizator.entity.User;
import sport.totalizator.service.exception.ServiceException;

public interface UserService {
    User registerUser(User user) throws ServiceException;
}
