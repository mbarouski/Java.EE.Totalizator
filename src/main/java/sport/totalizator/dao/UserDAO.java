package sport.totalizator.dao;

import sport.totalizator.dao.exception.DAOException;
import sport.totalizator.entity.User;

import java.util.List;

public interface UserDAO {
    public List<User> getAllUsers() throws DAOException;
}
