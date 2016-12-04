package sport.totalizator.service;

import sport.totalizator.entity.User;
import sport.totalizator.service.exception.ServiceException;

public interface UserService {
    User registerUser(String login, String password, String confirmPassword, String email) throws ServiceException;

    User login(String login, String password) throws ServiceException;
}
