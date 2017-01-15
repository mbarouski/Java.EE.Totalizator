package sport.totalizator.service;

import sport.totalizator.entity.User;
import sport.totalizator.exception.UserException;
import sport.totalizator.service.exception.ServiceException;

import java.util.List;

public interface UserService {
    User registerUser(String login, String password, String confirmPassword, String email) throws ServiceException, UserException;

    User login(String login, String password) throws ServiceException, UserException;

    User getFullUserInformationByLogin(String login) throws  ServiceException;

    List<User> getAllUsers() throws ServiceException;

    void banUsers(List<Integer> idList) throws ServiceException;

    void unbanUsers(List<Integer> idList) throws ServiceException;

    void changeRoleForUsers(List<Integer> idList, String role) throws ServiceException;
}
