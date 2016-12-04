package sport.totalizator.service.impl;

import org.apache.log4j.Logger;
import sport.totalizator.dao.UserDAO;
import sport.totalizator.dao.exception.DAOException;
import sport.totalizator.dao.factory.DAOFactory;
import sport.totalizator.dao.impl.UserDAOImpl;
import sport.totalizator.entity.User;
import sport.totalizator.service.UserService;
import sport.totalizator.service.exception.ServiceException;
import sport.totalizator.util.MD5Converter;

public class UserServiceImpl implements UserService {
    private static final Logger log = Logger.getLogger(UserDAOImpl.class);
    private static final UserServiceImpl instance = new UserServiceImpl();
    private UserDAO userDAO;

    public static UserServiceImpl getInstance(){
        return instance;
    }

    UserServiceImpl(){
        userDAO = DAOFactory.getFactory().getUserDAO();
    }

    @Override
    public User registerUser(String login, String password, String confirmPassword, String email) throws ServiceException {
        User user = new User();
        user.setEmail(email);
        user.setLogin(login);
        user.setPassHash(MD5Converter.getHash(password));
        try {
            user = userDAO.createUser(user);
        }
        catch (DAOException exc){
            log.error(exc);
            throw new ServiceException(exc);
        }
        return user;
    }

    @Override
    public User login(String login, String password) throws ServiceException {
        try {
            User user = userDAO.getUserByLogin(login);
            if((user != null) && (user.getPassHash().equals(MD5Converter.getHash(password)))) {
                return user;
            }
        } catch (DAOException exc){
            log.error(exc);
            throw new ServiceException(exc);
        }
        return null;
    }
}
