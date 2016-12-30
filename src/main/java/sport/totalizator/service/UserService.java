package sport.totalizator.service;

import sport.totalizator.entity.User;
import sport.totalizator.exception.UserException;
import sport.totalizator.service.exception.ServiceException;

public interface UserService {
    User registerUser(String login, String password, String confirmPassword, String email) throws ServiceException, UserException;

    User login(String login, String password) throws ServiceException, UserException;
}
