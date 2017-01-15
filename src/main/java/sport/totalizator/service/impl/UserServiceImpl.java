package sport.totalizator.service.impl;

import org.apache.log4j.Logger;
import sport.totalizator.dao.RateDAO;
import sport.totalizator.dao.UserDAO;
import sport.totalizator.dao.exception.DAOException;
import sport.totalizator.dao.factory.DAOFactory;
import sport.totalizator.entity.User;
import sport.totalizator.exception.UserException;
import sport.totalizator.service.UserService;
import sport.totalizator.service.exception.ServiceException;
import sport.totalizator.util.MD5Converter;

import java.util.List;

public class UserServiceImpl implements UserService {
    private static final Logger log = Logger.getLogger(UserServiceImpl.class);
    private static final UserServiceImpl instance = new UserServiceImpl();
    private UserDAO userDAO;
    private RateDAO rateDAO;

    public static UserServiceImpl getInstance(){
        return instance;
    }

    UserServiceImpl(){
        userDAO = DAOFactory.getFactory().getUserDAO();
        rateDAO = DAOFactory.getFactory().getRateDAO();
    }

    @Override
    public User registerUser(String login, String password, String confirmPassword, String email)
            throws ServiceException, UserException {
        User user = new User();
        if((email == null) || (email.isEmpty()))
            throw new ServiceException("email is empty or null");
        user.setEmail(email);
        if((login == null) || (login.isEmpty())){
            throw new ServiceException("login is empty or null");
        }
        user.setLogin(login);
        if((password == null) || (password.isEmpty()) || (confirmPassword == null)
                || (confirmPassword.isEmpty()) || (!password.equals(confirmPassword))){
            throw new ServiceException("password or passowrd confirmation is invalid");
        }
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
    public User login(String login, String password) throws ServiceException, UserException {
        try {
            User user = userDAO.getUserByLogin(login);
            if((user != null) && (user.getPassHash().equals(MD5Converter.getHash(password)))) {
                return user;
            }
            else {
                User errorUser = new User();
                errorUser.setLogin(login);
                throw new UserException("err.password-or-login-incorrect", errorUser);
            }
        } catch (DAOException exc){
            log.error(exc);
            throw new ServiceException(exc);
        }
    }

    @Override
    public User getFullUserInformationByLogin(String login) throws ServiceException {
        try {
            User user = userDAO.getFullUserInformationByLogin(login);
            user.setActiveRates(rateDAO.getActiveRatesForUser(user.getUserId()));
            user.setFinishedRates(rateDAO.getFinishedRatesForUser(user.getUserId()));
            return user;
        } catch (DAOException exc){
            log.error(exc);
            throw new ServiceException(exc);
        }
    }

    @Override
    public List<User> getAllUsers() throws ServiceException {
        try{
            return userDAO.getAllUsers();
        } catch (DAOException exc){
            log.error(exc);
            throw new ServiceException(exc);
        }
    }

    @Override
    public void banUsers(List<Integer> idList) throws ServiceException {
        try{
            userDAO.banUsers(idList);
        } catch (DAOException exc){
            log.error(exc);
            throw new ServiceException(exc);
        }
    }

    @Override
    public void unbanUsers(List<Integer> idList) throws ServiceException {
        try{
            userDAO.unbanUsers(idList);
        } catch (DAOException exc){
            log.error(exc);
            throw new ServiceException(exc);
        }
    }

    @Override
    public void changeRoleForUsers(List<Integer> idList, String role) throws ServiceException {
        try{
            userDAO.changeRoleForUsers(idList, role);
        } catch (DAOException exc){
            log.error(exc);
            throw new ServiceException(exc);
        }
    }
}
