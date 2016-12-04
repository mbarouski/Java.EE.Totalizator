package sport.totalizator.service.impl;

import sport.totalizator.entity.User;
import sport.totalizator.service.UserService;
import sport.totalizator.service.exception.ServiceException;

public class UserServiceImpl implements UserService {
    @Override
    public User registerUser(User user) throws ServiceException {
        return user;
    }
}
