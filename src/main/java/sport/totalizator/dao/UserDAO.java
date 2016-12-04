package sport.totalizator.dao;

import sport.totalizator.dao.exception.DAOException;
import sport.totalizator.entity.User;

import java.util.List;

public interface UserDAO {
    List<User> getAllUsers() throws DAOException;

    User createUser(User user) throws DAOException;

    User getUserByLogin(String login) throws DAOException;
}
