package sport.totalizator.dao;

import sport.totalizator.dao.exception.DAOException;
import sport.totalizator.entity.User;
import sport.totalizator.exception.ExceptionWithErrorList;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

/**
 * UserDAO is interface that defines methods for interaction of service layer with database from side of {@link User}.
 */
public interface UserDAO {
    /**
     * Method returns a list of all {@link User}s.
     * @return
     * @throws DAOException
     */
    List<User> getAllUsers() throws DAOException;

    /**
     * Method adds {@link User} to database.
     * @param user
     * @return
     * @throws DAOException
     * @throws ExceptionWithErrorList
     */
    User createUser(User user) throws DAOException, ExceptionWithErrorList;

    /**
     * Method returns {@link User} object by his login.
     * @param login
     * @return
     * @throws DAOException
     */
    User getUserByLogin(String login) throws DAOException;

    /**
     * Method returns {@link User} object by his login with some else info.
     * @param login
     * @return
     * @throws DAOException
     */
    User getFullUserInformationByLogin(String login) throws DAOException;

    /**
     * Method returns {@link User}'s id by login.
     * @param login
     * @return
     * @throws DAOException
     */
    int getUserIdByLogin(String login) throws DAOException;

    /**
     * Method fills up {@link User}'s balance.
     * @param connection
     * @param userId
     * @param money
     * @throws DAOException
     */
    void fillUpBalanceForUser(Connection connection, int userId, BigDecimal money) throws DAOException;

    /**
     * Method withdraws money from {@link User}'s balance.
     * @param connection
     * @param userId
     * @param money
     * @throws DAOException
     */
    void withdrawMoneyFromUser(Connection connection, int userId, BigDecimal money) throws DAOException;

    /**
     * Method checks sufficiency of money amount on {@link User}'s balance.
     * @param userId
     * @param money
     * @return
     * @throws DAOException
     */
    boolean haveMoney(int userId, BigDecimal money) throws DAOException;

    /**
     * Method bans users.
     * @param idList
     * @throws DAOException
     */
    void banUsers(List<Integer> idList) throws DAOException;

    /**
     * Method unbans users.
     * @param idList
     * @throws DAOException
     */
    void unbanUsers(List<Integer> idList) throws DAOException;

    /**
     * Method changes {@link User}'s roles.
     * @param idList
     * @param role
     * @throws DAOException
     */
    void changeRoleForUsers(List<Integer> idList, String role) throws DAOException;
}
