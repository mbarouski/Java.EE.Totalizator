package sport.totalizator.service;

import sport.totalizator.entity.User;
import sport.totalizator.exception.ExceptionWithErrorList;
import sport.totalizator.service.exception.ServiceException;

import java.util.List;

/**
 * {@link UserService} provides methods for interaction of controller and DAO layer from side of user.
 */
public interface UserService {
    /**
     * Method checks data for registration and manages this process.
     * @param login
     * @param password
     * @param confirmPassword
     * @param email
     * @return
     * @throws ServiceException
     * @throws ExceptionWithErrorList
     */
    User registerUser(String login, String password, String confirmPassword, String email) throws ServiceException, ExceptionWithErrorList;

    /**
     * Method checks data for logging in and manages this process.
     * @param login
     * @param password
     * @return
     * @throws ServiceException
     * @throws ExceptionWithErrorList
     */
    User login(String login, String password) throws ServiceException, ExceptionWithErrorList;

    /**
     * Method returns full information about {@link User} by login.
     * @param login
     * @return
     * @throws ServiceException
     */
    User getFullUserInformationByLogin(String login) throws  ServiceException;

    /**
     * Method returns list of all {@link User}s.
     * @return
     * @throws ServiceException
     */
    List<User> getAllUsers() throws ServiceException;

    /**
     * Method checks data for banning users and manages this process.
     * @param idList
     * @throws ServiceException
     */
    void banUsers(List<Integer> idList) throws ServiceException;

    /**
     * Method checks data for unbanning users and manages this process.
     * @param idList
     * @throws ServiceException
     */
    void unbanUsers(List<Integer> idList) throws ServiceException;

    /**
     * Method checks data for role changing of users and manages this process.
     * @param idList
     * @throws ServiceException
     */
    void changeRoleForUsers(List<Integer> idList, String role) throws ServiceException;
}
